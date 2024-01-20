package com.example.rentacarv1.services.dtos.responses.car;

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
    private static final long serialVersionUID = 8775378630877700674L;

    private int id;
    private double daily_price;
    private int kilometer;
    private String plate;
    private int year;
    private GetModelResponse modelResponse;
    private GetColorResponse colorResponse;

}
