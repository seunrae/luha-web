package com.example.luha.models;

import com.example.luha.BREADTYPE;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Entity
@Table(name = "bread_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Bread extends BaseEntity{
    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    private BREADTYPE breadType;
    @Column(nullable = false)
    private Integer quantity;

    private String imagePath;
    @ManyToMany(mappedBy = "shoppingCart")
    private List<User> users;
}
