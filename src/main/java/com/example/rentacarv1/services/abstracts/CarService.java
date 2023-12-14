package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetByIdCarResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;

import java.util.List;

public interface CarService {

    List<GetCarResponse> getAll();

    GetByIdCarResponse getById(int id);

    void add(AddCarRequest addCarRequest);

    void update(UpdateCarRequest updateCarRequest);

    void delete(int id);

}
