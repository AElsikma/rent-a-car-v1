package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String username);
}
