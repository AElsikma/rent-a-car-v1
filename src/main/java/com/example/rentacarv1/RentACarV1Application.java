package com.example.rentacarv1;

import com.example.rentacarv1.core.utilities.exceptions.types.BusinessException;
import com.example.rentacarv1.core.utilities.exceptions.problemDetails.ProblemDetails;
import com.example.rentacarv1.core.utilities.exceptions.problemDetails.ValidationProblemDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@SpringBootApplication
@RestControllerAdvice
public class RentACarV1Application {

    public static void main(String[] args) {
        SpringApplication.run(RentACarV1Application.class, args);
    }






}
