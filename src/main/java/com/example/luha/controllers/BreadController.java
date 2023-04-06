package com.example.luha.controllers;

import com.example.luha.Dtos.BreadDto;
import com.example.luha.models.Bread;
import com.example.luha.service.BreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/breads")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class BreadController {
    private final BreadService breadService;
    @PostMapping(path = "/add-bread")
    public ResponseEntity<Bread> addBread(@RequestBody BreadDto breadDto){
        return breadService.addBread(breadDto);
    }


    @PatchMapping(path = "/update-bread/{breadId}")
    public ResponseEntity<Bread> updateBread(@RequestBody BreadDto breadDto, @PathVariable Long breadId){
        return breadService.updateBread(breadDto, breadId);
    }


    @DeleteMapping(path = "/remove-bread/{breadId}")
    public ResponseEntity<String> removeBread(@PathVariable Long breadId){
        return breadService.removeBread(breadId);
    }

    @GetMapping(path = "/user/bread/{breadId}")
    public ResponseEntity<Bread> getBread(@PathVariable Long breadId){
        return breadService.getBread(breadId);
    }
    @GetMapping(path = "/user/all-breads")
    public ResponseEntity<List<Bread>> getBreads(){
        return breadService.getAllBread();
    }



}
