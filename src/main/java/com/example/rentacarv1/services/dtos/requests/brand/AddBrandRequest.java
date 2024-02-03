package com.example.rentacarv1.services.dtos.requests.brand;

import com.example.rentacarv1.services.constants.brand.BrandMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBrandRequest {

    @NotBlank(message = BrandMessages.BRAND_NOT_BLANK)
    @Size(min = 2, max = 20, message = BrandMessages.NAME_SHOULD_BE_BETWEEN_2_AND_20_CHARACTERS)
    private String name;

}

