package com.events.products.data.dto;

import com.events.products.data.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "First name is required")
    @Size(min = 3, message = "First name must be at least 3 characters")
    @Pattern(regexp = "^[A-Za-z']+$", message = "First name must contain only letters and apostrophes")
    private String firstName;

    @NotBlank(message = "Second name is required")
    @Size(min = 3, message = "Second name must be at least 3 characters")
    @Pattern(regexp = "^[A-Za-z']+$", message = "Second name must contain only letters and apostrophes")
    private String secondName;

    @Size(min = 3, message = "Third name must be at least 3 characters")
    @Pattern(regexp = "^[A-Za-z']*$", message = "Third name must contain only letters and apostrophes")
    private String thirdName; // Optional

    @NotBlank(message = "Last name is required")
    @Size(min = 3, message = "Last name must be at least 3 characters")
    @Pattern(regexp = "^[A-Za-z']+$", message = "Last name must contain only letters and apostrophes")
    private String lastName;

    private Integer points;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    private String profilePicturePath;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+9627[7-9][0-9]{7}$", message = "Phone number must be a valid Jordanian mobile number (+9627XXXXXXXX)")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must include at least one uppercase letter, one number, and one special character")
    private String password;

    @NotBlank(message = "Birth date is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])-([0-2][0-9]|3[01])-(19|20)\\d{2}$",
            message = "Birth date must be in MM-DD-YYYY format")
    private String birthDate;

    @NotBlank(message = "Address is required")
    @Pattern(regexp = "^(?=.*\\b(city|state|street)\\b).*$",
            message = "Address must include city, state, and street name")
    private String address;

    private RoleEnum role;
}
