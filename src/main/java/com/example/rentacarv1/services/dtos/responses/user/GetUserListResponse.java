package com.example.rentacarv1.services.dtos.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListResponse {

    private String name;
    private String surname;
    private int gsm;
    private String email;
}
