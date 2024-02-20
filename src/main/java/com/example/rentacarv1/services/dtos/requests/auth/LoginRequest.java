package com.example.rentacarv1.services.dtos.requests.auth;

import com.example.rentacarv1.services.constants.user.UserMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {


    @NotBlank(message = UserMessages.USER_NOT_BLANK)
    @Email(message = UserMessages.EMAIL_FORMAT)
    private String email;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.*])(?=\\S+$).{8,}",
            message =UserMessages.PASSWORD_FORMAT)
    private String password;
}