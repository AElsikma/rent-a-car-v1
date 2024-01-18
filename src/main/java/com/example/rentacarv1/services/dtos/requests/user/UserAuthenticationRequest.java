package com.example.rentacarv1.services.dtos.requests.user;

import com.example.rentacarv1.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthenticationRequest {
    private String name;
    private String surname;
    private String email;
    private  String password;
    private  String gsm;
    private List<Role> roles;
}
