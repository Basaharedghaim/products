package com.events.products.data.entity;

import com.events.products.data.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "USER_INFO")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "THIRD_NAME")
    private String thirdName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "POINTS")
    private Integer points;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BIRTH_DATE")
    private String birthDate;

    @Column(name = "PROFILE_PICTURE_PATH")
    private String profilePicturePath;

    @Column(name = "UPDATED_ON")
    private LocalDate updatedOn;

    @Column(name = "CREATED_ON")
    private LocalDate createdOn;

    @Column(name = "ROLE")
    private RoleEnum role;


}
