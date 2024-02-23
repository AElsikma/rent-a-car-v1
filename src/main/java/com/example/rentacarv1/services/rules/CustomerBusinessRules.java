package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.types.BusinessException;
import com.example.rentacarv1.repositories.CustomerRepository;
import com.example.rentacarv1.services.constants.customer.CustomerMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;

    public void checkIfCustomerByIdExists(Integer id) {
        if (!this.customerRepository.existsById(id)) {
            throw new BusinessException(CustomerMessages.ID_NOT_FOUND);
        }
    }
}
