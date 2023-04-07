package com.example.springcommerce.repository;

import com.example.springcommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    public Customer findCustomerByUsernameLike(String username);

    //UserDetailsServiceImpl
    @Query(nativeQuery = true,
            value = "SELECT r.name FROM roles r " +
                    "INNER JOIN customers cus ON r.id = cus.id_role " +
                    "WHERE cus.username = :username")
    List<String> findRolesByUsername(@Param("username") String username);
    Customer findByUsername(String username);
}
