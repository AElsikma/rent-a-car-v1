package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.dtos.requests.customer.AddCustomerRequest;
import com.example.rentacarv1.services.dtos.requests.customer.UpdateCustomerRequest;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerListResponse;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerResponse;

import java.util.List;

public interface CustomerService {
    DataResult<List<GetCustomerListResponse>> getAll();
    DataResult<GetCustomerResponse> getById(int id);
    Result add (AddCustomerRequest addCustomerRequest);
    Result update (UpdateCustomerRequest updateCustomerRequest);
    Result delete (int id);
    boolean getCustomerById(Integer id);
}
