package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Integer> {

    boolean existsByName(String colorName);

    List<Model> findByBrandId(Integer id);

}
