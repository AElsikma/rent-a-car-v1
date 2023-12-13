package com.example.rentacarv1.services.dtos.requests.car;

import com.example.rentacarv1.Entities.Color;
import com.example.rentacarv1.Entities.Model;
import com.example.rentacarv1.Entities.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCarRequest {

    private int kilometer;
    private int year;
    private Double dailyPrice;
    private String plate;
    private int model_id;
    private Color color_id;
}
