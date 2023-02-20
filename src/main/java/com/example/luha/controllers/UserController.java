package com.example.luha.controllers;

import com.example.luha.Dtos.UserResponseDto;
import com.example.luha.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path= "/api/v1/")
public class UserController {
    private final UserService userService;
   @PreAuthorize("hasRole('USER')")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return userService.getAllUsers();
    }

}
