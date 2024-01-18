package com.example.rentacarv1.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    MODERATOR,
    MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
