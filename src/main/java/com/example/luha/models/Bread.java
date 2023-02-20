package com.example.luha.models;

import com.example.luha.BREADTYPE;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
@Entity
@Table(name = "bread_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bread extends BaseEntity{
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private BREADTYPE breadType;
    @Column(nullable = false)
    private Integer quantity;
    @ManyToMany(mappedBy = "shoppingCart")
    private List<User> users;
}
