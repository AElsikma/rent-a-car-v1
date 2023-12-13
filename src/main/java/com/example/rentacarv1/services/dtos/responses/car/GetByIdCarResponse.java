package com.example.rentacarv1.services.dtos.responses.car;

import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCarResponse {
    private int id;
    private double daily_price;
    private int kilometer;
    private String plate;
    private int year;
    private GetModelResponse modelResponse;
    private GetColorResponse colorResponse;
}
