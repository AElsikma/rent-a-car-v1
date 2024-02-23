package com.example.rentacarv1.services.dtos.responses.car;

import com.example.rentacarv1.entities.enums.CarState;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarResponse  implements Serializable {
    private int id;
    private double dailyPrice;
    private String imagePath;
    private int kilometer;
    private String plate;
    private int year;
    private CarState carState;
    private GetModelResponse modelResponse;
    private GetColorResponse colorResponse;
}
