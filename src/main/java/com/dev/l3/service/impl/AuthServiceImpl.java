package com.dev.l3.service.impl;

import com.dev.l3.dto.request.LoginRequest;
import com.dev.l3.dto.request.RegisterRequest;
import com.dev.l3.dto.request.TokenRequest;
import com.dev.l3.dto.response.AuthResponse;
import com.dev.l3.entity.Role;
import com.dev.l3.entity.User;
import com.dev.l3.exception.AppException;
import com.dev.l3.exception.ErrorMess;
import com.dev.l3.repository.RoleRepository;
import com.dev.l3.repository.UserRepository;
import com.dev.l3.service.AuthService;
import com.dev.l3.service.JwtService;
import com.dev.l3.utils.constants.AppConst;
import com.dev.l3.utils.enums.RoleEnum;
import com.dev.l3.utils.validator.AppValidate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    JwtService jwtService;
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorMess.USER_NOT_EXISTED));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorMess.INCORRECT_PASSWORD);
        }
        Map<String, String> tokens = jwtService.buildToken(user);

        return AuthResponse.builder()
                .accessToken(tokens.get(AppConst.ACCESS_TOKEN))
                .refreshToken(tokens.get(AppConst.REFRESH_TOKEN))
                .isAuthenticated(true)
                .build();
    }

    @Override
    public void register(RegisterRequest request) {
        AppValidate.checkDuplicate(userRepository.existsByUsername(request.getUsername()), ErrorMess.USER_ALREADY_EXISTED);
        AppValidate.checkDuplicate(userRepository.existsByCode(request.getCode()), ErrorMess.CODE_ALREADY_EXISTED);
        AppValidate.isMatch(request.getPassword(), request.getConfirmPassword());
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .code(request.getCode())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new HashSet<>(Set.of(checkRole())))
                .build();
        userRepository.save(user);
    }

    @Override
    public AuthResponse refresh(TokenRequest request) {
        return AuthResponse.builder()
                .accessToken(jwtService.refreshToken(request.getToken()))
                .build();
    }

    @Override
    public void logout(String token) {
        jwtService.logout(token);
    }
    Role checkRole() {
        return roleRepository.findByRoleName(RoleEnum.ROLE_MANAGER.name())
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .roleName(RoleEnum.ROLE_MANAGER.name())
                        .build()));
    }


}
