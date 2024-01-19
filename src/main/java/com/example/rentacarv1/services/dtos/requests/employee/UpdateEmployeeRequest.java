package com.example.rentacarv1.services.dtos.requests.employee;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {

    @NotNull(message = "Id can not be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;

    @Min(value = 0,message = "Salary must be greater than or equal to 0.")
    private double salary;

}
