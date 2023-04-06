package com.example.luha.service;

import com.example.luha.Dtos.BreadDto;
import com.example.luha.Dtos.BreadResponseDto;
import com.example.luha.models.Bread;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BreadService {
    ResponseEntity<String> uploadBreadPhoto(MultipartFile file) throws IOException;
    ResponseEntity<BreadResponseDto> buyBread(BreadDto breadDto);
    ResponseEntity<Bread> addBread(BreadDto breadDto);
    ResponseEntity<Bread> updateBread(BreadDto breadDto, Long id);
    ResponseEntity<String> removeBread(Long id);
    ResponseEntity<Bread> getBread(Long id);
    ResponseEntity<List<Bread>> getAllBread();


}
