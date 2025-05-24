package com.dev.l3.controller;

import com.dev.l3.dto.request.LoginRequest;
import com.dev.l3.dto.request.RegisterRequest;
import com.dev.l3.dto.request.TokenRequest;
import com.dev.l3.dto.response.ApiResponse;
import com.dev.l3.dto.response.AuthResponse;
import com.dev.l3.service.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.build(authService.login(request));
    }

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return ApiResponse.build(null);
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@RequestBody TokenRequest req) {
        return ApiResponse.build(authService.refresh(req));
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody TokenRequest req) {
        authService.logout(req.getToken());
        return ApiResponse.build(null);
    }

}
