package com.example.rentacarv1.services.dtos.requests.brand;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBrandRequest {
    @NotBlank(message = "Brand name can not be empty!")
    @Min(value = 2,message = "Brand name must be min 2!")
    private String name;
}
