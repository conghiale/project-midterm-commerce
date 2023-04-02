package com.example.springcommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private int quantity;
    private double price;
    private String color;
    private String description;
    private String image;

    @NonNull
    @JoinColumn(name = "brand_id")
    @ManyToOne
    private Brand brandId;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @ManyToMany(mappedBy = "products")

    private Set<Customer> customers = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();

    public Product() {
        super();
    }

    public Product(String name, int quantity, double price, String color, String description, String image, @NonNull Brand brandId) {
        super();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.color = color;
        this.description = description;
        this.brandId = brandId;
        this.image = image;
    }

    public Product(int id, String name, int quantity, double price, String color, String description, String image, @NonNull Brand brandId) {
        super();
        Id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.color = color;
        this.description = description;
        this.brandId = brandId;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", brandId=" + brandId +
                '}';
    }
}
