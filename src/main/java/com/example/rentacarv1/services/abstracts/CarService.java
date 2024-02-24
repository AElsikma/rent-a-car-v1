package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.entities.enums.CarState;
import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetCarListResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;

import java.util.List;

public interface CarService {

    DataResult<List<GetCarListResponse>> getAll();

    DataResult<GetCarResponse> getById(int id);

    Result add(AddCarRequest addCarRequest);

    Result update(UpdateCarRequest updateCarRequest);

    Result delete(int id);
    boolean getCarById(Integer id);
    void updateCarState(int carId, CarState newState);
    boolean isCarUnderMaintenance(int carId);
    boolean isCarRented(int carId);

}
