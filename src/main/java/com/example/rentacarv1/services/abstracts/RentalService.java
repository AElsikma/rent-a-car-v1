package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.dtos.requests.rental.AddRentalRequest;
import com.example.rentacarv1.services.dtos.requests.rental.UpdateRentalRequest;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalListResponse;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalResponse;

import java.util.List;

public interface RentalService {
    DataResult<List<GetRentalListResponse>> getAll();
    DataResult<GetRentalResponse> getById(int id);
    DataResult<List<GetRentalResponse>> getByCustomerId(int customerId);
    Result add (AddRentalRequest addRentalRequest);
    Result update (UpdateRentalRequest updateRentalRequest);
    Result delete (int id);
}
