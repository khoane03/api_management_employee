package com.dev.l3.configuration;

import com.dev.l3.exception.ErrorMess;
import com.dev.l3.service.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${define.secret-key}")
    @NonFinal
    String SECRET_KEY;


    JwtService jwtService;

    @NonFinal
    NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        if (!jwtService.validateToken(token, false)) {
            throw new JwtException(ErrorMess.TOKEN_INVALID.getMessage());
        }
        if (Objects.isNull(nimbusJwtDecoder)) {
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(new SecretKeySpec(SECRET_KEY.getBytes(), MacAlgorithm.HS512.getName()))
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }
        return nimbusJwtDecoder.decode(token);
    }
}
