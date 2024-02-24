package com.example.rentacarv1.services.dtos.responses.car;

import com.example.rentacarv1.entities.enums.CarState;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("CarList")
public class GetCarListResponse implements Serializable {
    public static final long serialVersionUID=4548968470391477304L;
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
