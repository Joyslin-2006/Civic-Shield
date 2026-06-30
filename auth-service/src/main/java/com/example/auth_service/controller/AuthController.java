package com.example.auth_service.controller;

import com.example.auth_service.dto.AuthResponse;
import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

@CrossOrigin(origins = "http://localhost:5174")

public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok("User Registered Successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
    @GetMapping("/validate")
    public ResponseEntity<String> validate(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        return ResponseEntity.ok(
                authService.validateToken(token)
        );
    }
}
