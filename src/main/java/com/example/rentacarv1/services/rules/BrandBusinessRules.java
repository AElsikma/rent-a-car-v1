package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.types.BusinessException;
import com.example.rentacarv1.repositories.BrandRepository;
import com.example.rentacarv1.services.constants.brand.BrandMessages;
import com.example.rentacarv1.services.constants.user.UserMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {

    private BrandRepository brandRepository;

    public void checkIfBrandNameExists(String name){
        if(this.brandRepository.existsByName(name)){
            throw new BusinessException(BrandMessages.BRAND_ALREADY_EXISTS);
        };
    }


}
