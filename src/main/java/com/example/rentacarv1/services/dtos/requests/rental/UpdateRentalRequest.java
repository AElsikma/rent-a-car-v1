package com.example.rentacarv1.services.dtos.requests.rental;

import com.example.rentacarv1.entities.concretes.Car;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

    @NotNull(message = "Id can not be null.")
    @Positive(message = "Id must be a positive number.")
    private  int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "The starting date cannot be further back than today.")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "The end date cannot be further back than today.")
    private LocalDate endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "The end date cannot be further back than today.")
    private LocalDate returnDate;

    @NotNull(message = "The kilometer field cannot be null !")
    @Min(value = 0,message = "Kilometer must be greater than or equal to 0.")
    private int startKilometer;

    @NotNull(message = "The kilometer field cannot be null !")
    @Min(value = 0,message = "Kilometer must be greater than or equal to 0.")
    private int endKilometer;

    @NotNull(message = "The total price field cannot be null !")
    @Min(value = 0,message = "Total Price must be greater than or equal to 0.")
    private double totalPrice;

    @Min(value = 0,message = "The discount value can not be lower than 0")
    private double discount;

    @NotBlank
    private Car car;


}


