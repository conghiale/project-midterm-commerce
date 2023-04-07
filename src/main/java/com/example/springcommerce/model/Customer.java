package com.example.springcommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "customers")
public class Customer {
    @Id
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private LocalDate dob;
    private String gender;
//    private HashMap<String, Integer> hashMapProduct;

    @OneToMany(mappedBy = "customerId")
    private Set<Order> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "customer_product",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    public Role role;

    public Customer() {
        this.id = UUID.randomUUID();
    }

    public Customer(String name,String username, String email, String password, LocalDate dob, String gender) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    public Customer(UUID id, String name, String username, String email, String password, LocalDate dob, String gender) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    public Customer(UUID id, String name, String username, String email, String password, LocalDate dob, String gender, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.role = role;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                '}';
    }
}
