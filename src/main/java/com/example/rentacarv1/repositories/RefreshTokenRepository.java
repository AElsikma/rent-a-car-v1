package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.concretes.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
}
