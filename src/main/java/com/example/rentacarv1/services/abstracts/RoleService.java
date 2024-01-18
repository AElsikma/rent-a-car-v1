package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.entities.Role;
import com.example.rentacarv1.repositories.RoleRepository;

public interface RoleService  {
    Role findByName(String username);
}
