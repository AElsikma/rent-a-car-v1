package com.example.rentacarv1.services.dtos.requests.user;

import com.example.rentacarv1.entities.User;
import com.example.rentacarv1.services.constants.user.UserMessages;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = UserMessages.USER_NOT_BLANK)
    @Size(min = 2, max = 20, message = UserMessages.NAME_SHOULD_BE_BETWEEN_2_AND_20_CHARACTERS)
    private String name;

    @NotBlank(message = UserMessages.USER_NOT_BLANK)
    @Size(min = 2, max = 20, message =UserMessages.NAME_SHOULD_BE_BETWEEN_2_AND_20_CHARACTERS)
    private String surname;

    @NotBlank(message = UserMessages.USER_NOT_BLANK)
    @Pattern(regexp = "05[0-9]{9}", message =UserMessages.PHONE_NUMBER_FORMAT)
    private String gsm;

    @NotBlank(message =UserMessages.USER_NOT_BLANK)
    @Email(message = UserMessages.EMAIL_FORMAT)
    private String email;
}
