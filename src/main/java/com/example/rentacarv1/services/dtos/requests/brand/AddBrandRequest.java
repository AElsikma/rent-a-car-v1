package com.example.rentacarv1.services.dtos.requests.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddBrandRequest {
    @NotBlank(message = "Brand name can not be empty!")
    @Size(min = 3,max = 20,message = "Brand name must be between 2 and 20!")
    private String name;
}
