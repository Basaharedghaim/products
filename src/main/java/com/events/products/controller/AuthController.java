package com.events.products.controller;


import com.events.products.business.service.AuthService;
import com.events.products.dto.login.LoginRequest;
import com.events.products.dto.login.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/api/v1")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getPhoneNumber(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    // Optional: Register endpoint for testing
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest request) {
        authService.register(request.getPhoneNumber(), request.getPassword());
        return ResponseEntity.ok("User registered");
    }
}
