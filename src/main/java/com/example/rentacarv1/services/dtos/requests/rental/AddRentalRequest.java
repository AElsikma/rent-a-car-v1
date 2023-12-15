package com.example.rentacarv1.services.dtos.requests.rental;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRentalRequest {
    @Positive(message = "enter only positive")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Past(message = "Just enter the startDate as YYYY-MM-DD")
    @NotEmpty(message = "startDate cannot be empty or null")
    @Future(message = "The starting date cannot be further back than today.")
    private LocalDate startDate;

    @Positive(message = "enter only positive")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Past(message = "Just enter the endDate as YYYY-MM-DD")
    @NotEmpty(message = "endDate cannot be empty or null")
    private LocalDate endDate;

    @NotEmpty(message = "cannot be empty or null")
    @Positive(message = "enter only positive")
    @Max(value = 2,message = "Enter the Car ID as a number between zero and two.")
    private int carId;

    @NotEmpty(message = "cannot be empty or null")
    @Positive(message = "enter only positive")
    @Max(value = 2,message = "Enter the customer ID as a number between zero and two.")
    private int customerId;

    @NotEmpty(message = "cannot be empty or null")
    @Positive(message = "enter only positive")
    @Max(value = 999999,message = "Enter the employee ID as a number")
    private int employeeId;

}
