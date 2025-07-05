package com.events.products.data.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String jwtToken;
    private String mobileNumber;

}
