package com.rikdev.crud.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String identification;
    private String email;
    private String password;
    private String username; // Opcional, si no se envía se puede usar el email
    private String f_creation;
    private String f_update;
}
