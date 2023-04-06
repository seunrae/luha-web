package com.example.luha.models;

import com.example.luha.ROLE;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.util.List;
@Entity
@Table(name = "user_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User extends BaseEntity{
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;

    private Boolean enabled = false;

    private Boolean locked = false;

    private String imagePath;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_bread",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bread_id")
    )
    private List<Bread> shoppingCart;

    @Enumerated(EnumType.ORDINAL)
    private ROLE userRole;
}
