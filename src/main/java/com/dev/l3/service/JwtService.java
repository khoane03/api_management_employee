package com.dev.l3.service;

import com.dev.l3.entity.User;

import java.util.Map;


public interface JwtService {
    Map<String, String> buildToken(User user);

    void logout(String token);

    boolean validateToken(String token, boolean isRefreshToken);

    String refreshToken(String token);


}
