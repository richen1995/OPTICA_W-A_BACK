package com.rikdev.crud.services;

public interface EmailService {
    void sendResetPasswordEmail(String to, String token);
}
