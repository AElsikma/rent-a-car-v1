package com.example.rentacarv1.services.dtos.requests.customer;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {
    @Digits(integer = 11,fraction = 0, message = "You can only enter integers")
    @Size(min=11,max=11,message = "Nationality Id can only be 11 characters")
    private String nationalityId;
}
