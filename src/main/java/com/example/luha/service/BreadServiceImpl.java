package com.example.luha.service;

import com.example.luha.BREADTYPE;
import com.example.luha.Dtos.BreadDto;
import com.example.luha.Dtos.BreadResponseDto;
import com.example.luha.Repositories.BreadRepository;
import com.example.luha.cloudinary.CloudiinaryUtils;
import com.example.luha.exception.UserNotFoundException;
import com.example.luha.models.Bread;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BreadServiceImpl implements BreadService{
    private final BreadRepository breadRepository;
    private final CloudiinaryUtils cloudiinaryUtils;


    @Override
    public ResponseEntity<String> uploadBreadPhoto(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public ResponseEntity<BreadResponseDto> buyBread(BreadDto breadDto) {
        return null;
    }

    @Override
    public ResponseEntity<Bread> addBread(BreadDto breadDto) {
        Bread bread = Bread.builder()
                .price(breadDto.getPrice())
                .breadType(BREADTYPE.valueOf(breadDto.getBreadType()))
                .quantity(breadDto.getQuantity())
                .build();
        breadRepository.save(bread);

        return ResponseEntity.ok(bread);
    }

    @Override
    public ResponseEntity<Bread> updateBread(BreadDto breadDto, Long id) {
        Bread bread = breadRepository.findById(id).orElseThrow( () -> new UserNotFoundException("Bread doesnt exist"));
        bread.setPrice(breadDto.getPrice());
        bread.setBreadType(BREADTYPE.valueOf(breadDto.getBreadType()));
        bread.setQuantity(breadDto.getQuantity());
//        bread = Bread.builder()
//                .price(breadDto.getPrice())
//                .breadType(breadDto.getBreadType())
//                .quantity(breadDto.getQuantity())
//                .build();
        breadRepository.save(bread);
        return ResponseEntity.ok(bread);
    }

    @Override
    public ResponseEntity<String> removeBread(Long id) {
        Bread bread = breadRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Bread doesnt exist"));
        breadRepository.delete(bread);
        return ResponseEntity.ok("Item removed successfully");
    }

    @Override
    public ResponseEntity<Bread> getBread(Long id) {
        Bread bread = breadRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Bread doesnt exist"));

        return ResponseEntity.ok(bread);
    }

    @Override
    public ResponseEntity<List<Bread>> getAllBread() {
        List<Bread> breadsList = breadRepository.findAll();

        return ResponseEntity.ok(breadsList);
    }
}
