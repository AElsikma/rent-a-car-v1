package com.example.rentacarv1.services.dtos.requests.employee;

import com.example.rentacarv1.services.constants.employee.EmployeeMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeRequest {

    @Min(value = 0,message = EmployeeMessages.SALARY_MUST_BE_POSITIVE_NUMBER)
    private double salary;

}
