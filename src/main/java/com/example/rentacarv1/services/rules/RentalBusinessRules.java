package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.types.BusinessException;
import com.example.rentacarv1.repositories.RentalRepository;
import com.example.rentacarv1.services.abstracts.CarService;

import com.example.rentacarv1.services.abstracts.CustomerService;
import com.example.rentacarv1.services.abstracts.EmployeeService;
import com.example.rentacarv1.services.abstracts.UserService;
import com.example.rentacarv1.services.constants.car.CarMessages;
import com.example.rentacarv1.services.constants.customer.CustomerMessages;

import com.example.rentacarv1.services.constants.employee.EmployeeMessages;
import com.example.rentacarv1.services.constants.rental.RentalMessages;
import com.example.rentacarv1.services.constants.user.UserMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class RentalBusinessRules {

    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private  final EmployeeService employeeService;
    private final UserService userService;

    public void existsByCarId(Integer id) {
        if (!carService.getCarById(id)) {
            throw new BusinessException(CarMessages.CAR_NOT_EXIST);
        }
    }

    public void existsByCustomerId(Integer id) {
        if (!customerService.getCustomerById(id)) {
            throw new BusinessException(CustomerMessages.CUSTOMER_NOT_EXIST);
        }
    }

    public void existsByEmployeeId(Integer id) {
        if (!employeeService.getEmployeeById(id)) {
            throw new BusinessException(EmployeeMessages.EMPLOYEE_NOT_EXIST);
        }
    }
    public void existsByUserId(Integer id) {
        if (!userService.getUserById(id)) {
            throw new BusinessException(UserMessages.USER_NOT_EXIST);
        }
    }

    public void checkIfRentalByStartDate(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now())) {
            throw new BusinessException(RentalMessages.START_DATE_CANNOT_BE_FURTHER_BACK_THAN_TODAY);
        }
    }

    public void checkIfRentalByEndDate(LocalDate endDate, LocalDate startDate) {
        if (endDate.isBefore(startDate)) {
            throw new BusinessException(RentalMessages.END_DATE_CANNOT_BEFORE_THAN_START_DATE);
        }
    }

    public void checkIfRentalByDateValid(LocalDate startDate, LocalDate endDate) {
        if (ChronoUnit.DAYS.between(startDate, endDate) > 25) {
            throw new BusinessException(RentalMessages.THE_RENTAL_DURATION_CANNOT_EXCEED_25_DAYS);
        }
    }

    public double calculateTotalPrice(LocalDate startDate, LocalDate endDate, Double dailyPrice) {
        long rentalTime = ChronoUnit.DAYS.between(startDate, endDate);
        rentalTime++;
        return rentalTime * dailyPrice;
    }

    public void checkIfRentalByIdExists(Integer id) {
        if (!this.rentalRepository.existsById(id)) {
            throw new BusinessException(RentalMessages.ID_NOT_FOUND);
        }
    }

}
