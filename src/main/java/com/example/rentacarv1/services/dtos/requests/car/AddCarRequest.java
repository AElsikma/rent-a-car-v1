package com.example.rentacarv1.services.dtos.requests.car;

import com.example.rentacarv1.services.constants.car.CarMessages;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddCarRequest {

    @NotNull(message = CarMessages.CAR_NOT_NULL)
    @Positive(message = CarMessages.POSITIVE_NUMBER)
    private double daily_price;


    @NotNull(message = CarMessages.CAR_NOT_NULL)
    @Min(value = 0,message = CarMessages.POSITIVE_NUMBER)
    private int kilometer;


    @NotBlank(message = CarMessages.CAR_NOT_BLANK)
    @Size(min=5,max=9,message = CarMessages.PLATE_SHOULD_BE_BETWEEN_5_AND_9_CHARACTERS)
    private String plate;


    @NotNull(message =CarMessages.CAR_NOT_NULL)
    @Min(value = 2005,message = CarMessages.YEAR_SHOULD_BE_GREATER_THAN_OR_EQUAL_TO_2005)
    @Max(value = 2024,message = CarMessages.YEAR_CANNOT_BE_HIGHER_THAN_2024)
    private int year;

    private MultipartFile file;

    @NotNull(message =CarMessages.CAR_NOT_NULL)
    @Positive(message =CarMessages.POSITIVE_NUMBER)
    private int colorId;

    @NotNull(message = CarMessages.CAR_NOT_NULL)
    @Positive(message = CarMessages.POSITIVE_NUMBER)
    private int modelId;

}
