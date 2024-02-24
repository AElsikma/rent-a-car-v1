package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
    List<Rental> findByCustomerId(int customerId);
}
