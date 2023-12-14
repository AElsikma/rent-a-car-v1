package com.example.rentacarv1.controllers;

import com.example.rentacarv1.services.abstracts.ColorService;
import com.example.rentacarv1.services.abstracts.EmployeeService;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.requests.employee.AddEmployeeRequest;
import com.example.rentacarv1.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeListResponse;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeesController {
    private EmployeeService employeeService;

    @GetMapping("/getAll")
    public List<GetEmployeeListResponse> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public GetEmployeeResponse getById(@PathVariable int id){
        return employeeService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest){
        this.employeeService.add(addEmployeeRequest);
    }


    @PutMapping("/update")
    public void update( @RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
        this.employeeService.update(updateEmployeeRequest);
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable int id){
        this.employeeService.delete(id);
    }
}
