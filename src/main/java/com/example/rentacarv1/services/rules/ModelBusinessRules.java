package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.BusinessException;
import com.example.rentacarv1.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {

    private ModelRepository modelRepository;

    public void existsByModel(String modelName){
        if(this.modelRepository.existsByName(modelName)){
            throw new BusinessException("There cannot be more than one model with the same model name");
        };
    };
}
