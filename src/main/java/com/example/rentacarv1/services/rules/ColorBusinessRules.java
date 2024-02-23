package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.types.BusinessException;
import com.example.rentacarv1.repositories.ColorRepository;
import com.example.rentacarv1.services.constants.car.CarMessages;
import com.example.rentacarv1.services.constants.color.ColorMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColorBusinessRules {

    private ColorRepository colorRepository;

    public void checkIfColorNameExists(String name){
        if(this.colorRepository.existsByName(name)){
            throw new BusinessException(ColorMessages.COLOR_ALREADY_EXISTS);
        };
    }

    public void checkIfColorByIdExists(Integer id) {
        if (!this.colorRepository.existsById(id)) {
            throw new BusinessException(ColorMessages.ID_NOT_FOUND);
        }
    }
}
