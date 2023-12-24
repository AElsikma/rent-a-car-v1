package com.example.rentacarv1.services.dtos.requests.brand;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
    @NotNull(message = "The brand id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;
    @NotBlank(message = "Brand name can not be empty!")
    @Min(value = 2,message = "Brand name must be min 2!")
    private String name;
}
