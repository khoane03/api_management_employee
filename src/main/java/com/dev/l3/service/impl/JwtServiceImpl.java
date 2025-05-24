package com.dev.l3.service.impl;

import com.dev.l3.entity.Token;
import com.dev.l3.entity.User;
import com.dev.l3.exception.AppException;
import com.dev.l3.exception.ErrorMess;
import com.dev.l3.repository.TokenRepository;
import com.dev.l3.repository.UserRepository;
import com.dev.l3.service.JwtService;
import com.dev.l3.utils.constants.AppConst;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${define.secret-key}")
    @NonFinal
    String SECRET_KEY;

    @Value("${define.access-token-expiration}")
    @NonFinal
    long ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${define.refresh-token-expiration}")
    @NonFinal
    long REFRESH_TOKEN_EXPIRATION_TIME;

    TokenRepository tokenRepository;
    UserRepository userRepository;

    @Override
    public Map<String, String> buildToken(User user) {
        String accessToken = generateToken(user, ACCESS_TOKEN_EXPIRATION_TIME);
        String refreshToken = generateToken(user, REFRESH_TOKEN_EXPIRATION_TIME);
        tokenRepository.findByUserId(user.getId())
                .ifPresentOrElse(token -> {
                            token.setAccessToken(accessToken);
                            token.setRefreshToken(refreshToken);
                            token.setValid(true);
                            tokenRepository.save(token);
                        },
                        () -> {
                            tokenRepository.save(Token.builder()
                                    .accessToken(accessToken)
                                    .refreshToken(refreshToken)
                                    .isValid(true)
                                    .userId(user.getId())
                                    .build());
                        });

        return Map.of(AppConst.ACCESS_TOKEN, accessToken, AppConst.REFRESH_TOKEN, refreshToken);
    }


    @Override
    public void logout(String accessToken) {
        var token = tokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new AppException(ErrorMess.TOKEN_INVALID));
        token.setValid(false);
        tokenRepository.save(token);
    }

    @Override
    public boolean validateToken(String token, boolean isRefreshToken) {
        try {

            SignedJWT signedJWT = SignedJWT.parse(token);

            JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
            if (!signedJWT.verify(verifier)) {
                return false;
            }

            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expirationTime == null || expirationTime.before(new Date())) {
                return false;
            }

            if (isRefreshToken) {
                return tokenRepository.findByRefreshToken(token)
                        .map(Token::isValid)
                        .orElse(false);
            } else {
                return tokenRepository.findByAccessToken(token)
                        .map(Token::isValid)
                        .orElse(false);
            }
        } catch (JOSEException | ParseException e) {
            return false;
        }
    }

    @Override
    public String refreshToken(String refreshToken) {
        if (!validateToken(refreshToken, true)) {
            throw new AppException(ErrorMess.TOKEN_EXPIRED);
        }

        var token = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AppException(ErrorMess.TOKEN_INVALID));

        var username = extractUsername(refreshToken);
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorMess.USER_NOT_EXISTED));
        String newAccessToken = generateToken(user, ACCESS_TOKEN_EXPIRATION_TIME);
        token.setAccessToken(newAccessToken);
        token.setValid(true);
        tokenRepository.save(token);
        return newAccessToken;
    }

    String generateToken(User user, long expirationTime) {
        try {
            // Create JWT header
            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS512).build();

            // Create JWT claims
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + expirationTime))
                    .claim(AppConst.SCOPE, buildScope(user))
                    .build();

            // Create signed JWT
            SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            // Sign the JWT
            signedJWT.sign(new MACSigner(SECRET_KEY.getBytes()));

            // Serialize to string
            return signedJWT.serialize();
        } catch (JOSEException e) {
            throw new AppException(ErrorMess.TOKEN_GENERATION_FAILED);
        }
    }

    String extractUsername(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            throw new AppException(ErrorMess.TOKEN_INVALID);
        }
    }

    String buildScope(User user) {
        return user.getRoles().stream()
                .map(role -> "ROLE_" + role.getRoleName())
                .collect(Collectors.joining(" "));
    }

}
