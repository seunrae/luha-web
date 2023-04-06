package com.example.luha.controllers;

import com.example.luha.Dtos.UserResponseDto;
import com.example.luha.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path= "/api/v1/user/")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(value = "/upload-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@RequestPart(name = "file")MultipartFile image) throws IOException {
        return userService.uploadProfilePicture(image);

    }

}
