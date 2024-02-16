package com.example.rentacarv1.services.dtos.requests.user;

import com.example.rentacarv1.entities.concretes.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email address format")
    private String email;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.*])(?=\\S+$).{8,}",
            message = "At least 8 characters\n" +
                    "\n" +
                    "Contains at least one digit\n" +
                    "\n" +
                    "Contains at least one lowercase and one uppercase letter\n" +
                    "\n" +
                    "Contains at least one special character from the set (@#%$^.*etc.)\n" +
                    "\n" +
                    "Does not contain spaces, tabs, etc.")
    private String password;


    @NotBlank
    private List<Role> roles;
}
