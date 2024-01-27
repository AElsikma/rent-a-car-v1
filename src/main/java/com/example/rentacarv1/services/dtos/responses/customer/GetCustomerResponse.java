package com.example.rentacarv1.services.dtos.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerResponse implements Serializable {
    private int id;

    private String nationalityId;
}
