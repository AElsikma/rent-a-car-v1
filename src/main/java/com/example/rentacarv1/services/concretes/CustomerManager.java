package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.configurations.cache.RedisCacheManager;
import com.example.rentacarv1.core.internationalization.MessageService;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Customer;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.CustomerRepository;
import com.example.rentacarv1.services.abstracts.CustomerService;
import com.example.rentacarv1.services.constants.baseMessage.BaseMessages;
import com.example.rentacarv1.services.dtos.requests.customer.AddCustomerRequest;
import com.example.rentacarv1.services.dtos.requests.customer.UpdateCustomerRequest;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerListResponse;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerResponse;
import com.example.rentacarv1.services.rules.CustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    private RedisCacheManager redisCacheManager;
    private final MessageService messageService;
    private final CustomerBusinessRules customerBusinessRules;
    @Override
    public DataResult<List<GetCustomerListResponse>> getAll() {
        List<GetCustomerListResponse> customerListResponses = (List<GetCustomerListResponse>) redisCacheManager.getCachedData("customerListCache", "getCustomersAndCache");
        if (customerListResponses == null) {
            customerListResponses = getCustomersAndCache();
            redisCacheManager.cacheData("customerListCache", "getCustomersAndCache", customerListResponses);
        }

        return new SuccessDataResult<>(customerListResponses, messageService.getMessage(BaseMessages.GET_ALL),HttpStatus.OK);
    }

    public List<GetCustomerListResponse> getCustomersAndCache() {
        List<Customer> customers = customerRepository.findAll();
        List<GetCustomerListResponse> customerListResponses = customers.stream()
                .map(customer -> modelMapperService.forResponse().map(customer, GetCustomerListResponse.class))
                .collect(Collectors.toList());
        return customerListResponses;
    }

    @Override
    public DataResult<GetCustomerResponse> getById(int id) {
        customerBusinessRules.checkIfCustomerByIdExists(id);
        Customer customer = customerRepository.findById(id).orElseThrow();
        GetCustomerResponse getCustomerResponse=this.modelMapperService.forResponse().map(customer,GetCustomerResponse.class);
        return new SuccessDataResult<GetCustomerResponse>(getCustomerResponse,messageService.getMessage(BaseMessages.GET), HttpStatus.OK);
    }

    @Override
    public Result add(AddCustomerRequest addCustomerRequest) {
        Customer customer=this.modelMapperService.forRequest().map(addCustomerRequest,Customer.class);
        this.customerRepository.save(customer);
        redisCacheManager.cacheData("customerListCache", "getCustomersAndCache", null);
        return new SuccessResult( HttpStatus.CREATED,messageService.getMessage(BaseMessages.ADD));
    }

    @Override
    public Result update(UpdateCustomerRequest updateCustomerRequest) {
        customerBusinessRules.checkIfCustomerByIdExists(updateCustomerRequest.getId());
        Customer customer =this.modelMapperService.forRequest().map(updateCustomerRequest,Customer.class);
        customerRepository.save(customer);
        redisCacheManager.cacheData("customerListCache", "getCustomersAndCache", null);
        return new SuccessResult( HttpStatus.OK,messageService.getMessage(BaseMessages.UPDATE));
    }

    @Override
    public Result delete(int id) {
        customerBusinessRules.checkIfCustomerByIdExists(id);
        customerRepository.deleteById(id);
        redisCacheManager.cacheData("customerListCache", "getCustomersAndCache", null);
        return new SuccessResult( HttpStatus.OK,messageService.getMessage(BaseMessages.DELETE));
    }

    @Override

    public boolean getCustomerById(Integer id) {

        return this.customerRepository.existsById(id);

    }
}
