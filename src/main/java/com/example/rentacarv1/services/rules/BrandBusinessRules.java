package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.BusinessException;
import com.example.rentacarv1.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {

    private BrandRepository brandRepository;

    public void existsByBrand(String brandName){
        if(this.brandRepository.existsByName(brandName)){
            throw new BusinessException("There cannot be more than one brand with the same brand name");
        };
    }
}
