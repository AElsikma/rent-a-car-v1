package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.entities.Role;
import com.example.rentacarv1.repositories.RoleRepository;
import com.example.rentacarv1.services.abstracts.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {
    private RoleRepository roleRepository;
    @Override
    public Role findByName(String username) {
        return roleRepository.findByName(username);
    }
}
