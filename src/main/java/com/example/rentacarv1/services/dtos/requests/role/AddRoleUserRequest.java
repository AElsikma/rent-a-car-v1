package com.example.rentacarv1.services.dtos.requests.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleUserRequest {
    private int id;
    private String name;
}
