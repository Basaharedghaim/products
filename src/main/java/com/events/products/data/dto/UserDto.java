package com.events.products.data.dto;

import com.events.products.data.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String thirdName; // Optional
    private String lastName;
    private Integer points;
    private String email;
    private String profilePicturePath;
    private String phoneNumber;
    private String password;
    private String birthDate;
    private String address;
    private RoleEnum role;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime updatedOn;

}
