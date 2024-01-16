package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Customer;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.CustomerRepository;
import com.example.rentacarv1.services.abstracts.CustomerService;
import com.example.rentacarv1.services.dtos.requests.customer.AddCustomerRequest;
import com.example.rentacarv1.services.dtos.requests.customer.UpdateCustomerRequest;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerListResponse;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    @Override
    public DataResult<List<GetCustomerListResponse>> getAll() {
        List<Customer> customers=customerRepository.findAll();
        List<GetCustomerListResponse> customerListResponse=customers.stream().map(customer -> this.modelMapperService.forResponse()
                .map(customer,GetCustomerListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetCustomerListResponse>>(customerListResponse,"Customers listed");
    }

    @Override
    public DataResult<GetCustomerResponse> getById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        GetCustomerResponse getCustomerResponse=this.modelMapperService.forResponse().map(customer,GetCustomerResponse.class);
        return new SuccessDataResult<GetCustomerResponse>(getCustomerResponse,"Customer Listed");
    }

    @Override
    public Result add(AddCustomerRequest addCustomerRequest) {
        Customer customer=this.modelMapperService.forRequest().map(addCustomerRequest,Customer.class);
        this.customerRepository.save(customer);
        return new SuccessResult("Customer added");
    }

    @Override
    public Result update(UpdateCustomerRequest updateCustomerRequest) {
        Customer customer =this.modelMapperService.forRequest().map(updateCustomerRequest,Customer.class);
        customerRepository.save(customer);
        return new SuccessResult("Customer updated");
    }

    @Override
    public Result delete(int id) {
        customerRepository.deleteById(id);
        return new SuccessResult("Customer deleted !");
    }
}
