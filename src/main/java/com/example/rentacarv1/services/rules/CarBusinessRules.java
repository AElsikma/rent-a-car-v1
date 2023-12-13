package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.Entities.Car;
import com.example.rentacarv1.core.utilities.exceptions.BusinessException;
import com.example.rentacarv1.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class CarBusinessRules {

    private CarRepository carRepository;



}
