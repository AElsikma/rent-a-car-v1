package com.example.rentacarv1.services.dtos.requests.brand;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddBrandRequest {
    @NotBlank(message = "Brand name can not be empty!")
    @Min(value = 2,message = "Brand name must be min 2!")
    private String name;
}
