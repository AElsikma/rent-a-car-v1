package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.types.BusinessException;
import com.example.rentacarv1.repositories.ModelRepository;
import com.example.rentacarv1.services.constants.model.ModelMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {

    private ModelRepository modelRepository;

    public void checkIfModelNameExists(String name){
        if(this.modelRepository.existsByName(name)){

            throw new BusinessException(ModelMessages.MODEL_ALREADY_EXISTS);
        };
    };
}
