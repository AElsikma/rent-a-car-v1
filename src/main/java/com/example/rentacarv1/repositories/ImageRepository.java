package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.concretes.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Integer> {

}
