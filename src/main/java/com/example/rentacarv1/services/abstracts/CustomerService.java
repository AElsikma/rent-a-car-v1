package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.services.dtos.requests.customer.AddCustomerRequest;
import com.example.rentacarv1.services.dtos.requests.customer.UpdateCustomerRequest;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerListResponse;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerResponse;

import java.util.List;

public interface CustomerService {
    List<GetCustomerListResponse> getAll();
    GetCustomerResponse getById(int id);
    void add (AddCustomerRequest addCustomerRequest);
    void update (UpdateCustomerRequest updateCustomerRequest);
    void delete (int id);
}
