package com.example.rentacarv1.controllers;

import com.example.rentacarv1.services.abstracts.ColorService;
import com.example.rentacarv1.services.abstracts.CustomerService;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.requests.customer.AddCustomerRequest;
import com.example.rentacarv1.services.dtos.requests.customer.UpdateCustomerRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerListResponse;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomersController {
    private CustomerService customerService;

    @GetMapping("/getAll")
    public List<GetCustomerListResponse> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public GetCustomerResponse getById(@PathVariable int id){
        return customerService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid AddCustomerRequest addCustomerRequest){
        this.customerService.add(addCustomerRequest);
    }


    @PutMapping("/update")
    public void update( @RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        this.customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable int id){
        this.customerService.delete(id);
    }
}
