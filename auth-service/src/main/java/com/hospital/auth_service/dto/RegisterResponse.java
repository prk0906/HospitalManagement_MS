package com.hospital.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {

    private Long userId;

    private String username;

    private String email;

    private String role;

    private String message;
}
