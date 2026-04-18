package com.rikdev.crud.services;

import com.rikdev.crud.dto.JwtResponse;
import com.rikdev.crud.dto.LoginRequest;
import com.rikdev.crud.dto.RegisterRequest;

public interface AuthService {
    JwtResponse login(LoginRequest request);

    void register(RegisterRequest request);

    void forgotPassword(String email);

    void resetPassword(String token, String newPassword);
}
