package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Employee;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.EmployeeRepository;
import com.example.rentacarv1.services.abstracts.EmployeeService;
import com.example.rentacarv1.services.dtos.requests.employee.AddEmployeeRequest;
import com.example.rentacarv1.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeListResponse;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;
    @Override
    public DataResult<List<GetEmployeeListResponse>> getAll() {
        List<Employee> employees=employeeRepository.findAll();
        List<GetEmployeeListResponse> employeeListResponse=employees.stream().map(employee -> this.modelMapperService.forResponse()
                .map(employee,GetEmployeeListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetEmployeeListResponse>>(employeeListResponse,"Employees listed");
    }

    @Override
    public DataResult<GetEmployeeResponse> getById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        GetEmployeeResponse getEmployeeResponse=this.modelMapperService.forResponse().map(employee,GetEmployeeResponse.class);
        return new SuccessDataResult<GetEmployeeResponse>(getEmployeeResponse,"Employee listed");
    }

    @Override
    public Result add(AddEmployeeRequest addEmployeeRequest) {
        Employee employee =this.modelMapperService.forRequest().map(addEmployeeRequest,Employee.class);
        this.employeeRepository.save(employee);
        return new SuccessResult("Employee added");
    }

    @Override
    public Result update(UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee =this.modelMapperService.forRequest().map(updateEmployeeRequest,Employee.class);
        employeeRepository.save(employee);
        return new SuccessResult("Employee updated");
    }

    @Override
    public Result delete(int id) {
        employeeRepository.deleteById(id);
        return new SuccessResult("Empoyee deleted !");
    }
}
