package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.dtos.requests.model.AddModelRequest;
import com.example.rentacarv1.services.dtos.requests.model.UpdateModelRequest;
import com.example.rentacarv1.services.dtos.responses.model.GetModelListResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelResponse;

import java.util.List;

public interface ModelService {
    DataResult<List<GetModelListResponse>> getAll();
    DataResult<GetModelResponse> getById(int id);

    DataResult<List<GetModelListResponse>> getByBrandId(int id);
    Result add(AddModelRequest addModelRequest);

    Result update(UpdateModelRequest updateModelRequest);
    Result delete(int id);

    boolean getModelById(Integer id);
}
