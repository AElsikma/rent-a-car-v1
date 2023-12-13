package com.example.rentacarv1.services.dtos.requests.color;

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
    @Size(min = 3,max = 20,message = "Color name must be between 2 and 20!")
    private String name;
}
