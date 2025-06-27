package com.events.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String lastName;
    private Integer points;
    private String email;
    private String profilePicturePath;
    private String phoneNumber;
    private String password;

    private String birthDate;
}
