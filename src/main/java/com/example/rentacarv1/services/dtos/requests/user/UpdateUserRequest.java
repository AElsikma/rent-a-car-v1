package com.example.rentacarv1.services.dtos.requests.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotNull(message = "Id can not be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;

    private String name;
    private String surname;
    private String gsm;
    private String email;
}
