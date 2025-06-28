package com.events.products.data.dto.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String phoneNumber;
    private String password;
}
