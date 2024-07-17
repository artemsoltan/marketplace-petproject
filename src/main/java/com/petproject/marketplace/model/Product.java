package com.petproject.marketplace.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Product(String title, String description, int price, User user) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.user = user;
    }
}