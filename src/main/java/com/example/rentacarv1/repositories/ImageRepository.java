package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ImageRepository extends JpaRepository<Image,Long> {
}
