package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.dtos.requests.brand.AddBrandRequest;
import com.example.rentacarv1.services.dtos.requests.brand.UpdateBrandRequest;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandListResponse;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandResponse;

import java.util.List;

public interface BrandService {
    DataResult<List<GetBrandListResponse>> getAll();
    DataResult<GetBrandResponse> getById(int id);
    Result add (AddBrandRequest addBrandRequest);
    Result update (UpdateBrandRequest updateBrandRequest);
    Result delete (int id);
}
