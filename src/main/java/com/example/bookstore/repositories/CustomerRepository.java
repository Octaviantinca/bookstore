package com.example.bookstore.repositories;

import com.example.bookstore.models.Author;
import com.example.bookstore.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByLastName(String lastName);
}
