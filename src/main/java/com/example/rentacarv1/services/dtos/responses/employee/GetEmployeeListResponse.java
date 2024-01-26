package com.example.rentacarv1.services.dtos.responses.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeListResponse  implements Serializable {
    private  int id;
    private double salary;
}
