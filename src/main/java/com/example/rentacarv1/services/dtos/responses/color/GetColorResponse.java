package com.example.rentacarv1.services.dtos.responses.color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetColorResponse implements Serializable {
    private static final long serialVersionUID = 8775378630877700674L;

    private  int id;
    private String name;
}
