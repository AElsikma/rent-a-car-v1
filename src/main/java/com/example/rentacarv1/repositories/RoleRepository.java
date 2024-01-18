package com.example.rentacarv1.repositories;

import com.example.rentacarv1.entities.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String username);
}
