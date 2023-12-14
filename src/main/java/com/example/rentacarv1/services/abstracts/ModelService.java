package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.services.dtos.requests.model.AddModelRequest;
import com.example.rentacarv1.services.dtos.requests.model.UpdateModelRequest;
import com.example.rentacarv1.services.dtos.responses.model.GetModelListResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelResponse;

import java.util.List;

public interface ModelService {
    List<GetModelListResponse> getAll();
    void add(AddModelRequest addModelRequest);
    GetModelResponse getById(int id);
    void update(UpdateModelRequest updateModelRequest);
    void delete(int id);
}
