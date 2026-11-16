package com.hospital.auth_service.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;

    private String tokenType;

    private Long userId;

    private String username;

    private String email;

    private String role;
    private String message;
}
