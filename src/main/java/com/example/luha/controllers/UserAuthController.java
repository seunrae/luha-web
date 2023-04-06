package com.example.luha.controllers;

import com.example.luha.Dtos.UserLoginDto;
import com.example.luha.Dtos.UserRegisterDto;
import com.example.luha.Dtos.UserResponseDto;
import com.example.luha.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserAuthController {

    private final UserService userService;

    @PostMapping("/Register")
    public ResponseEntity<UserResponseDto> userRegister(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userService.userRegister(userRegisterDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> userRegister(@RequestBody UserLoginDto userLoginDto){
        return userService.userLogin(userLoginDto);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }




}
