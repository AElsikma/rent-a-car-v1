package com.example.rentacarv1.services.dtos.requests.car;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {

    @NotNull(message = "The daily price field cannot be null !")
    @DecimalMin(value = "0.0",inclusive = false,message = "Daily price must be greater than 0.")
    private double daily_price;


    @NotNull(message = "The kilometer field cannot be null !")
    @Min(value = 0,message = "Kilometer must be greater than or equal to 0.")
    private int kilometer;


    @NotBlank(message = "The plate field can't be empty.")
    @Pattern(regexp = "(0[1-9]|[1-7][0-9]|8[01]) [A-Z]{1,3}(\\d{4})",message = "Invalid licence plate")
    @Size(min=5,max=9,message = "Licence plate must be between 5 and 9 characters")
    private String plate;


    @NotNull(message = "The year field cannot be null")
    @Min(value = 2005,message = "Year must be greater than or equal to 2005.")
    @Max(value = 2024, message ="Year must be less than or equal to the current yaer." )
    private int year;




}
