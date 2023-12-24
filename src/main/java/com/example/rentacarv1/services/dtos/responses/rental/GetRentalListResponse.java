package com.example.rentacarv1.services.dtos.responses.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalListResponse {

    private LocalDate startDate;


    private LocalDate endDate;


    private LocalDate returnDate;


    private int startKilometer;


    private Long endKilometer;


    private Double totalPrice;




}
