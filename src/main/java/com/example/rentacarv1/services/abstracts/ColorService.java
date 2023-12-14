package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;

import java.util.List;

public interface ColorService {

    List<GetColorListResponse> getAll();
    GetColorResponse getById(int id);
    void add(AddColorRequest addColorRequest);

    void update(UpdateColorRequest updateColorRequest);

    void delete(int id);



}
