package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {

    boolean existsByPlate(String plate);

}
