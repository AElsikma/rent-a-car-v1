package com.example.rentacarv1.repositories;

import com.example.rentacarv1.Entities.Color;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color,Integer> {

}
