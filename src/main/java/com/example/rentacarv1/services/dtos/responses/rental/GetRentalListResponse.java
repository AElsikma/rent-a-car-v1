package com.example.rentacarv1.services.dtos.responses.rental;

import com.example.rentacarv1.services.dtos.responses.car.GetCarListResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalListResponse  implements Serializable {
    private int id;

    private LocalDate startDate;


    private LocalDate endDate;


    private LocalDate returnDate;


    private int startKilometer;


    private Long endKilometer;


    private Double totalPrice;
    private GetCarResponse carResponse;




}
