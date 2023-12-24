package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetByIdCarResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;

import java.util.List;

public interface CarService {

    DataResult<List<GetCarResponse>> getAll();

    DataResult<GetByIdCarResponse> getById(int id);

    Result add(AddCarRequest addCarRequest);

    Result update(UpdateCarRequest updateCarRequest);

    Result delete(int id);

}
