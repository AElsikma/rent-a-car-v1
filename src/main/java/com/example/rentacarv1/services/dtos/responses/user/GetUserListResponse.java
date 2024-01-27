package com.example.rentacarv1.services.dtos.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListResponse implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String gsm;
    private String email;
}
