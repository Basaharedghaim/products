package com.events.products.business.service;

import com.events.products.business.exception.InvalidPasswordException;
import com.events.products.business.exception.UserNotFoundException;
import com.events.products.data.entity.UserEntity;
import com.events.products.data.repository.UserRepository;
import com.events.products.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;



    public String login(String phoneNumber, String rawPassword) {
        UserEntity user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return jwtUtil.generateToken(user.getPhoneNumber());
        } else {
            throw new InvalidPasswordException("Invalid password");
        }
    }
    public UserEntity getUserFromToken(String token) {
        if (!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Invalid JWT token");
        }

        String phoneNumber = jwtUtil.extractPhoneNumber(token);
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User not found for phone: " + phoneNumber));
    }

}

