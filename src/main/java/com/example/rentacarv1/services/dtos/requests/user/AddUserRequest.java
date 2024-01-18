package com.example.rentacarv1.services.dtos.requests.user;

import com.example.rentacarv1.services.dtos.requests.role.AddRoleUserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String name;
    private String surname;
    private String gsm;
    private String email;
    private String password;
    private List<AddRoleUserRequest> roles;

}
