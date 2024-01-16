package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.concretes.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Integer> {
    boolean existsByName(String colorName);

}
