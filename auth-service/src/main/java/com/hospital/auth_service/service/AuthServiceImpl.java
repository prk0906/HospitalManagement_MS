package com.hospital.auth_service.service;

import com.hospital.auth_service.Exception.DuplicateResourceException;
import com.hospital.auth_service.Repository.IAuthRepository;
import com.hospital.auth_service.dto.AuthResponse;
import com.hospital.auth_service.dto.LoginRequest;
import com.hospital.auth_service.dto.RegisterRequest;
import com.hospital.auth_service.dto.RegisterResponse;
import com.hospital.auth_service.entities.Role;
import com.hospital.auth_service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private IAuthRepository _repo;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public RegisterResponse register(RegisterRequest request) {
        if(_repo.findByUsername(request.getUsername()).isPresent()){
            throw new DuplicateResourceException( "Username already exists");
        }
        if(_repo.findByEmail(request.getEmail()).isPresent())
            throw new DuplicateResourceException( "User Email already exists");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.PATIENT);
        user.setActive(true);

        User save = _repo.save(user);
        return new RegisterResponse(
                save.getId(),
                save.getUsername(),
                save.getEmail(),
                save.getRole().name(),
                "User registered successfully"
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // 1. Authenticate username and password
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        User user = _repo.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(!user.isActive())
            throw new DisabledException("User account is disabled");
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .disabled(!user.isActive())
                .build();
        String token = jwtService.generateToken(userDetails, user.getRole().name());
        return new AuthResponse(
                token,
                "Bearer",
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name(),
                "Login Succesfull"
        );
    }
}
