package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.configurations.cache.RedisCacheManager;
import com.example.rentacarv1.core.internationalization.MessageService;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Employee;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.EmployeeRepository;
import com.example.rentacarv1.services.abstracts.EmployeeService;
import com.example.rentacarv1.services.constants.baseMessage.BaseMessages;
import com.example.rentacarv1.services.dtos.requests.employee.AddEmployeeRequest;
import com.example.rentacarv1.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeListResponse;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeResponse;
import com.example.rentacarv1.services.rules.EmployeeBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;
    private RedisCacheManager redisCacheManager;
    private final MessageService messageService;
    private  final EmployeeBusinessRules employeeBusinessRules;
    @Override
    public DataResult<List<GetEmployeeListResponse>> getAll() {
        List<GetEmployeeListResponse> employeeListResponses = (List<GetEmployeeListResponse>) redisCacheManager.getCachedData("employeeListCache", "getEmployeesAndCache");
        if (employeeListResponses == null) {
            employeeListResponses = getEmployeesAndCache();
            redisCacheManager.cacheData("employeeListCache", "getEmployeesAndCache", employeeListResponses);
        }

        return new SuccessDataResult<>(employeeListResponses, messageService.getMessage(BaseMessages.GET_ALL),HttpStatus.OK);
    }

    public List<GetEmployeeListResponse> getEmployeesAndCache() {
        List<Employee> employees = employeeRepository.findAll();
        List<GetEmployeeListResponse> employeeListResponses = employees.stream()
                .map(employee -> modelMapperService.forResponse().map(employee, GetEmployeeListResponse.class))
                .collect(Collectors.toList());
        return employeeListResponses;
    }

    @Override
    public DataResult<GetEmployeeResponse> getById(int id) {
        employeeBusinessRules.checkIfEmployeeByIdExists(id);
        Employee employee = employeeRepository.findById(id).orElseThrow();
        GetEmployeeResponse getEmployeeResponse=this.modelMapperService.forResponse().map(employee,GetEmployeeResponse.class);
        return new SuccessDataResult<GetEmployeeResponse>(getEmployeeResponse, messageService.getMessage(BaseMessages.GET), HttpStatus.OK);
    }

    @Override
    public Result add(AddEmployeeRequest addEmployeeRequest) {
        Employee employee =this.modelMapperService.forRequest().map(addEmployeeRequest,Employee.class);
        this.employeeRepository.save(employee);
        redisCacheManager.cacheData("employeeListCache", "getEmployeesAndCache", null);
        return new SuccessResult( HttpStatus.CREATED,messageService.getMessage(BaseMessages.ADD));
    }

    @Override
    public Result update(UpdateEmployeeRequest updateEmployeeRequest) {
        employeeBusinessRules.checkIfEmployeeByIdExists(updateEmployeeRequest.getId());
        Employee employee =this.modelMapperService.forRequest().map(updateEmployeeRequest,Employee.class);
        employeeRepository.save(employee);
        redisCacheManager.cacheData("employeeListCache", "getEmployeesAndCache", null);
        return new SuccessResult( HttpStatus.OK, messageService.getMessage(BaseMessages.UPDATE));
    }

    @Override
    public Result delete(int id) {
        employeeBusinessRules.checkIfEmployeeByIdExists(id);
        employeeRepository.deleteById(id);
        redisCacheManager.cacheData("employeeListCache", "getEmployeesAndCache", null);
        return new SuccessResult( HttpStatus.OK, messageService.getMessage(BaseMessages.DELETE));
    }
    @Override

    public boolean getEmployeeById(Integer id) {

        return this.employeeRepository.existsById(id);

    }
}
