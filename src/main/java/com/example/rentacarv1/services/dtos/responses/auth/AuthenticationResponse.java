package com.example.rentacarv1.services.dtos.responses.auth;

import com.example.rentacarv1.entities.User;
import com.example.rentacarv1.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    String accessToken;
    String refreshToken;
    private Role role;
    private int id;
}
