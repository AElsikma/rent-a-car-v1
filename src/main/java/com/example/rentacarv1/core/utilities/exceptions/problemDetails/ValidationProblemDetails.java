package com.example.rentacarv1.core.utilities.exceptions.problemDetails;

import com.example.rentacarv1.core.utilities.exceptions.problemDetails.ProblemDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ValidationProblemDetails extends ProblemDetails {
    private Map<String,String> validationErrors;         //hangi alanda ne hatası var

}
