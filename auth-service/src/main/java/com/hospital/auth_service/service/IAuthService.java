package com.hospital.auth_service.service;

import com.hospital.auth_service.dto.AuthResponse;
import com.hospital.auth_service.dto.LoginRequest;
import com.hospital.auth_service.dto.RegisterRequest;
import com.hospital.auth_service.dto.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {
    RegisterResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
