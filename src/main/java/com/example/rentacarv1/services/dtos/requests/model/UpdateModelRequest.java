package com.example.rentacarv1.services.dtos.requests.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {
    @NotNull(message = "ID can not be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;
    @NotBlank(message = "Model name can not be empty!")
    @Size(min = 3,max = 20,message = "Model name must be between 2 and 20!")
    private String name;
    @NotNull(message = "The brand id can not be null.")
    @Positive(message = "Id must be a positive number.")
    private int brandId;
}
