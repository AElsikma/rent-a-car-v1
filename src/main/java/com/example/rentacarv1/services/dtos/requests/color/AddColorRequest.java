package com.example.rentacarv1.services.dtos.requests.color;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddColorRequest {
    @NotBlank(message = "Color name can not be empty!")
    @Size(min = 2, message = "Color name must be at least 2 characters long!")
    private String name;
}
