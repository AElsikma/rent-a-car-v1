package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.entities.concretes.Role;

public interface RoleService  {
    Role findByName(String username);
}
