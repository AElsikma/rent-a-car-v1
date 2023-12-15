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
    @Min(value = 2,message = "Model name must be min 2!")
    private String name;

    @NotNull(message = "The brand id can not be null.")
    @Positive(message = "Id must be a positive number.")
    private int brandId;
}
