package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
