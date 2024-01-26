package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.config.cache.RedisCacheManager;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.entities.concretes.Car;
import com.example.rentacarv1.entities.concretes.Customer;
import com.example.rentacarv1.entities.concretes.Employee;
import com.example.rentacarv1.entities.concretes.Rental;
import com.example.rentacarv1.repositories.*;
import com.example.rentacarv1.services.abstracts.RentalService;
import com.example.rentacarv1.services.dtos.requests.rental.AddRentalRequest;
import com.example.rentacarv1.services.dtos.requests.rental.UpdateRentalRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetCarListResponse;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalListResponse;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalResponse;
import com.example.rentacarv1.services.rules.RentalBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;
    private final RentalBusinessRules rentalBusinessRules;
    private RedisCacheManager redisCacheManager;


    @Override
    public DataResult<List<GetRentalListResponse>> getAll() {
        List<GetRentalListResponse> rentalListResponses = (List<GetRentalListResponse>) redisCacheManager.getCachedData("rentalListCache", "getRentalsAndCache");
        if (rentalListResponses == null) {
            rentalListResponses= getRentalsAndCache();
            redisCacheManager.cacheData("rentalListCache", "getRentalsAndCache", rentalListResponses);
        }

        return new SuccessDataResult<>(rentalListResponses, "Rentals Listed.",HttpStatus.OK);
    }

    public List<GetRentalListResponse> getRentalsAndCache() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetRentalListResponse> rentalListResponses = rentals.stream()
                .map(rental -> modelMapperService.forResponse().map(rental, GetRentalListResponse.class))
                .collect(Collectors.toList());
        return rentalListResponses;
    }

    @Override
    public DataResult<GetRentalResponse> getById(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse getRentalResponse=this.modelMapperService.forResponse().map(rental,GetRentalResponse.class);
        return new SuccessDataResult<GetRentalResponse>(getRentalResponse,"Rental listed", HttpStatus.OK) ;
    }

    @Override
    public Result add(AddRentalRequest addRentalRequest) {

        Customer customer = customerRepository.findById(Integer.valueOf(addRentalRequest.getCustomerId()))
                .orElseThrow(()-> new IllegalArgumentException("The specified user was not found"));
        Car car = carRepository.findById(Integer.valueOf(addRentalRequest.getCarId()))
                .orElseThrow(()-> new IllegalArgumentException("The specified car was not found"));
        Employee employee = employeeRepository.findById(Integer.valueOf(addRentalRequest.getEmployeeId()))
                .orElseThrow(()-> new IllegalArgumentException("The specified user was not found"));

        Rental rental=this.modelMapperService.forRequest().map(addRentalRequest,Rental.class);
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setEmployee(employee);
        rental.setStartKilometer(car.getKilometer());
        int rentalLimit = rental.getStartDate().until(rental.getEndDate()).getDays() + 1;
        rental.setTotalPrice(car.getDailyPrice() * rentalLimit);
        this.rentalRepository.save(rental);
        redisCacheManager.cacheData("rentalListCache", "getRentalsAndCache", null);
        return new SuccessResult( HttpStatus.CREATED,"Rental added");

    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        Rental rental =this.modelMapperService.forRequest().map(updateRentalRequest,Rental.class);
        rentalRepository.save(rental);
        redisCacheManager.cacheData("rentalListCache", "getRentalsAndCache", null);
        return new SuccessResult( HttpStatus.OK,"Rental updated");
    }

    @Override
    public Result delete(int id) {

        rentalRepository.deleteById(id);
        redisCacheManager.cacheData("rentalListCache", "getRentalsAndCache", null);
        return new SuccessResult( HttpStatus.OK,"Rental deleted !");
    }
}
