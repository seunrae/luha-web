package com.example.luha.Dtos;
import com.example.luha.ROLE;
import lombok.Data;

@Data
public class UserRegisterDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    private ROLE userRole;

}
