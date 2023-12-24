package com.example.rentacarv1.services.abstracts;


import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.dtos.requests.employee.AddEmployeeRequest;
import com.example.rentacarv1.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeListResponse;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeResponse;

import java.util.List;

public interface EmployeeService {
    DataResult<List<GetEmployeeListResponse>> getAll();
    DataResult<GetEmployeeResponse> getById(int id);
    Result add (AddEmployeeRequest addEmployeeRequest);
    Result update (UpdateEmployeeRequest updateEmployeeRequest);
    Result delete (int id);
}
