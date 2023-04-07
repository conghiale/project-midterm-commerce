package com.example.springcommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate createAt;
    private String address;
    private String phone;
    private double cost;

    @ManyToMany
    @JoinTable (
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    @NonNull
    @JoinColumn (name = "customer_id")
    @ManyToOne
    private Customer customerId;

    public Order() {
        super();
    }

    public Order(double cost, Set<Product> products, @NonNull Customer customerId) {
        super();
        this.cost = cost;
        this.createAt = LocalDate.now();
        this.address = "19 Đ. Nguyễn Hữu Thọ, Tân Hưng, Quận 7, Thành phố Hồ Chí Minh";
        this.phone = "0826611778";
        this.products = products;
        this.customerId = customerId;
    }

    public Order(int id, double cost, LocalDate createAt, String address, String phone, Set<Product> products, @NonNull Customer customerId) {
        super();
        this.id = id;
        this.cost = cost;
        this.createAt = createAt;
        this.address = address;
        this.phone = phone;
        this.products = products;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cost=" + cost +
                ", createAt=" + createAt +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", products=" + products +
                ", customerId=" + customerId +
                '}';
    }
}
