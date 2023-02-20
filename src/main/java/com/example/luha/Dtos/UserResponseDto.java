package com.example.luha.Dtos;

import com.example.luha.ROLE;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private ROLE userRole;

    private final String token;
}
