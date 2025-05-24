package com.dev.l3.service;

import com.dev.l3.dto.request.LoginRequest;
import com.dev.l3.dto.request.RegisterRequest;
import com.dev.l3.dto.request.TokenRequest;
import com.dev.l3.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    void logout(String token);

    void register(RegisterRequest request);

    AuthResponse refresh(TokenRequest request);

}
