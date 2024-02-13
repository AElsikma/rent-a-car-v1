package com.example.rentacarv1.services.dtos.requests.customer;

import com.example.rentacarv1.services.constants.customer.CustomerMessages;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {

    @Digits(integer = 11, fraction = 0, message = CustomerMessages.ONLY_ENTER_INTEGER)
    @Size(min = 11, max = 11, message = CustomerMessages.ONLY_BE_11_CHARACTER)
    private String nationalityId;
}

