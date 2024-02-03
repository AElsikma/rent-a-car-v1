package com.example.rentacarv1.services.dtos.requests.customer;

import com.example.rentacarv1.services.constants.customer.CustomerMessages;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    @NotNull(message = CustomerMessages.CUSTOMER_NOT_NULL)
    @Positive(message = CustomerMessages.POSITIVE_NUMBER)
    private int id;

    @Digits(integer = 11, fraction = 0, message = CustomerMessages.ONLY_ENTER_INTEGER)
    @Size(min = 11, max = 11, message = CustomerMessages.ONLY_BE_11_CHARACTER)
    private String nationalityId;
}
