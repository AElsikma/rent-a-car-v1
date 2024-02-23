package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.types.BusinessException;
import com.example.rentacarv1.repositories.EmployeeRepository;
import com.example.rentacarv1.services.constants.customer.CustomerMessages;
import com.example.rentacarv1.services.constants.employee.EmployeeMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeBusinessRules {
    private final EmployeeRepository employeeRepository;
    public void checkIfEmployeeByIdExists(Integer id) {
        if (!this.employeeRepository.existsById(id)) {
            throw new BusinessException(EmployeeMessages.ID_NOT_FOUND);
        }
    }
}
