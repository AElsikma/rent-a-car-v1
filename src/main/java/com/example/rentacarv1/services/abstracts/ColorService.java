package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;

import java.util.List;

public interface ColorService {

    DataResult<List<GetColorListResponse>> getAll();
    DataResult<GetColorResponse> getById(int id);
    Result add(AddColorRequest addColorRequest);

    Result update(UpdateColorRequest updateColorRequest);

    Result delete(int id);



}
