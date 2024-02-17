package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.types.BusinessException;
import com.example.rentacarv1.repositories.CarRepository;
import com.example.rentacarv1.services.constants.car.CarMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class CarBusinessRules {

    private CarRepository carRepository;

  public void existsByPlate(String plate){
        if(this.carRepository.existsByPlate(plate)){
        throw new BusinessException(CarMessages.CAR_ALREADY_EXISTS);
    }}

    public String plateValidator(String plate){
        plate=plate.replaceAll("\s","").toUpperCase();
        String regex = "^(0[1-9]|[1-7][0-9]|8[01])[A-Z]{1,3}\\d{2,4}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(plate);
        if (!matcher.matches()) {
            throw new RuntimeException(CarMessages.PLATE_FORMAT);
        }
        return plate;
    }


}
