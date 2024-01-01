package com.example.rentacarv1.services.dtos.requests.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddModelRequest {

    @NotBlank(message = "Model name can not be empty!")
    @Size(min = 2, message = "Model name must be at least 2 characters long!")
    private String name;

    @NotNull(message = "The brand id can not be null.")
    @Positive(message = "Id must be a positive number.")
    private int brandId;
}
