package com.example.luha.service;

import com.example.luha.Dtos.UserLoginDto;
import com.example.luha.Dtos.UserRegisterDto;
import com.example.luha.Dtos.UserResponseDto;
import com.example.luha.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UserService {
    UserResponseDto userRegister(UserRegisterDto userRegisterDto);
    UserResponseDto userResponse(User user);
    ResponseEntity<UserResponseDto> userLogin(UserLoginDto userLoginDto);

    ResponseEntity<List<UserResponseDto>> getAllUsers();

     String confirmToken(String token);
     int enableUser(String email);

     ResponseEntity<String> uploadProfilePicture(MultipartFile file) throws IOException;
}
