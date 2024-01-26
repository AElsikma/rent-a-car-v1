package com.example.rentacarv1.services.dtos.responses.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBrandResponse  implements Serializable {
    private int id;
    private String name;

}
