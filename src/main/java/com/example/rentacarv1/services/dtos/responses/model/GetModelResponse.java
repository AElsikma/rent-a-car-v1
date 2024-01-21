package com.example.rentacarv1.services.dtos.responses.model;

import com.example.rentacarv1.services.dtos.responses.brand.GetBrandResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelResponse implements Serializable {

    private int id;
    private String name;
}
