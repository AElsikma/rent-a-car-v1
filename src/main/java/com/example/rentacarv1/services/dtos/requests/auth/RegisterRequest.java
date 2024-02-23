package com.example.rentacarv1.services.dtos.requests.auth;


import com.example.rentacarv1.entities.User;
import com.example.rentacarv1.entities.enums.Role;
import com.example.rentacarv1.services.constants.user.UserMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = UserMessages.USER_NOT_BLANK)
    @Size(min = 2, max = 20, message = UserMessages.NAME_SHOULD_BE_BETWEEN_2_AND_20_CHARACTERS)
    private String name;

    @NotBlank(message = UserMessages.USER_NOT_BLANK)
    @Size(min = 2, max = 20, message = UserMessages.NAME_SHOULD_BE_BETWEEN_2_AND_20_CHARACTERS)
    private String surname;

    @NotBlank(message = UserMessages.USER_NOT_BLANK)
    @Pattern(regexp = "05[0-9]{9}", message = UserMessages.PHONE_NUMBER_FORMAT)
    private String gsm;

    @NotBlank(message = UserMessages.USER_NOT_BLANK)
    @Email(message = UserMessages.EMAIL_FORMAT)
    private String email;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.*])(?=\\S+$).{8,}",
            message =UserMessages.PASSWORD_FORMAT)
    private String password;







}
