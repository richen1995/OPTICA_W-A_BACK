package com.rikdev.crud.controllers;

import com.rikdev.crud.dto.ForgotPasswordRequest;
import com.rikdev.crud.dto.JwtResponse;
import com.rikdev.crud.dto.LoginRequest;
import com.rikdev.crud.dto.RegisterRequest;
import com.rikdev.crud.dto.ResetPasswordRequest;
import com.rikdev.crud.dto.UserStatusRequest;
import com.rikdev.crud.entities.User;
import com.rikdev.crud.services.AuthService;
import com.rikdev.crud.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request.getEmail());
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request.getToken(), request.getNewPassword());
    }

    // 🟢 GESTIÓN DE USUARIOS
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PatchMapping("/users/{id}/status")
    public void updateUserStatus(@PathVariable Long id, @RequestBody UserStatusRequest request) {
        userService.updateUserStatus(id, request.isActive());
    }
}
