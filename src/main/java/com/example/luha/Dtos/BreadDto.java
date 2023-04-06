package com.example.luha.Dtos;

import com.example.luha.BREADTYPE;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class BreadDto {
    private BigDecimal price;
    private String breadType;
    private Integer quantity;
}
