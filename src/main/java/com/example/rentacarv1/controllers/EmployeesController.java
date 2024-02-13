package com.example.rentacarv1.controllers;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.abstracts.EmployeeService;
import com.example.rentacarv1.services.dtos.requests.employee.AddEmployeeRequest;
import com.example.rentacarv1.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeListResponse;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
@CrossOrigin
public class EmployeesController {
    private EmployeeService employeeService;

    @GetMapping("/getAll")
    public DataResult<List<GetEmployeeListResponse>> getAll(){
        return this.employeeService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetEmployeeResponse> getById(@PathVariable int id){
        return this.employeeService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Result add(@RequestBody @Valid() AddEmployeeRequest addEmployeeRequest){
       return this.employeeService.add(addEmployeeRequest);

    }


    @PutMapping("/update")
    public Result update( @RequestBody @Valid() UpdateEmployeeRequest updateEmployeeRequest){
        return this.employeeService.update(updateEmployeeRequest);
    }

    @DeleteMapping("/{id}")
    public Result delete( @PathVariable int id){

        return  this.employeeService.delete(id);
    }
}
