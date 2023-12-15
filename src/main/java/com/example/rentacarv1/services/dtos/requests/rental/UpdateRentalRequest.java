package com.example.rentacarv1.services.dtos.requests.rental;

import com.example.rentacarv1.Entities.Car;
import com.example.rentacarv1.Entities.Customer;
import com.example.rentacarv1.Entities.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

    private LocalDate startDate;


    private LocalDate endDate;


    private LocalDate returnDate;


    private int startKilometer;


    private int endKilometer;


    private double totalPrice;


    private double discount;


    private Car car;


}


