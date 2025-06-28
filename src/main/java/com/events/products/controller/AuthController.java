package com.events.products.controller;


import com.events.products.business.service.AuthService;
import com.events.products.dto.login.LoginRequest;
import com.events.products.dto.login.LoginResponse;
import com.events.products.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(new LoginResponse(token, request.getPhoneNumber()));
    }
    @PostMapping("/validate-user")
    public ResponseEntity<LoginResponse> validateUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String token = authHeader.substring(7);
        UserEntity user = authService.getUserFromToken(token);

        return ResponseEntity.ok(new LoginResponse(token,user.getPhoneNumber())); // or any info you want to return
    }


}
