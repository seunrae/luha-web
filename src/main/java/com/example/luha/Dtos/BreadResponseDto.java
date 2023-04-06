package com.example.luha.Dtos;

import com.example.luha.BREADTYPE;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
@Data
public class BreadResponseDto {
    private BigDecimal price;
    private BREADTYPE breadType;
    private Integer quantity;
}
