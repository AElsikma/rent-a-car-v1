package com.example.rentacarv1.services.dtos.requests.role;

import com.example.rentacarv1.services.constants.user.UserMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleUserRequest {

    @NotNull(message = UserMessages.USER_NOT_NULL)
    @Positive(message = UserMessages.POSITIVE_NUMBER)
    private int id;

    @NotBlank(message = UserMessages.USER_NOT_BLANK)
    @Size(min = 2, max = 20, message = UserMessages.NAME_SHOULD_BE_BETWEEN_2_AND_20_CHARACTERS)
    private String name;
}
