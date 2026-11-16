package com.hospital.auth_service.Controller;

import com.hospital.auth_service.dto.AuthResponse;
import com.hospital.auth_service.dto.LoginRequest;
import com.hospital.auth_service.dto.RegisterRequest;
import com.hospital.auth_service.dto.RegisterResponse;
import com.hospital.auth_service.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private  IAuthService _authService;
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        RegisterResponse response = _authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
           @RequestBody LoginRequest request) {

        AuthResponse response = _authService.login(request);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/profile")
    public ResponseEntity<String> profile() {
        return ResponseEntity.ok("Authenticated successfully");
    }
}
