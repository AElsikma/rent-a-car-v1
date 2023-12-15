package com.example.rentacarv1.repositories;

import com.example.rentacarv1.Entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color,Integer> {
    boolean existsByName(String colorName);

}
