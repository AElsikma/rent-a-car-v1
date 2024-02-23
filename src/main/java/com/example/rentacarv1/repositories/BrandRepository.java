package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    boolean existsByName(String brandName);
    boolean getBrandById(Integer id);

}
