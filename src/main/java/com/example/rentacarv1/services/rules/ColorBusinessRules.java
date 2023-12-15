package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.BusinessException;
import com.example.rentacarv1.repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColorBusinessRules {

    private ColorRepository colorRepository;

    public void existsByColor(String colorName){
        if(this.colorRepository.existsByName(colorName)){
            throw new BusinessException("There cannot be more than one color with the same color name");
        };
    };
}
