package com.example.rentacarv1.entities.enums;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER,
    ADMIN,
    GUEST,
    MODERATOR;

    @Override
    public String getAuthority() {
        return name();
    }

}
