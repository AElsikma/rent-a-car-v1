package com.example.rentacarv1.services.dtos.requests.user;

import com.example.rentacarv1.services.constants.user.UserMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotNull(message = UserMessages.USER_NOT_NULL)
    @Positive(message = UserMessages.POSITIVE_NUMBER)
    private int id;

    private String name;
    private String surname;
    private String gsm;
    private String email;
}
