package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.Entities.Customer;
import com.example.rentacarv1.Entities.Rental;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.CustomerRepository;
import com.example.rentacarv1.repositories.EmployeeRepository;
import com.example.rentacarv1.repositories.RentalRepository;
import com.example.rentacarv1.services.abstracts.RentalService;
import com.example.rentacarv1.services.dtos.requests.rental.AddRentalRequest;
import com.example.rentacarv1.services.dtos.requests.rental.UpdateRentalRequest;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalListResponse;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetRentalListResponse> getAll() {
        List<Rental> rentals=rentalRepository.findAll();
        List<GetRentalListResponse> rentalListResponse=rentals.stream().map(rental -> this.modelMapperService.forResponse()
                .map(rental,GetRentalListResponse.class)).collect(Collectors.toList());

        return rentalListResponse;
    }

    @Override
    public GetRentalResponse getById(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse getRentalResponse=this.modelMapperService.forRequest().map(rental,GetRentalResponse.class);
        return getRentalResponse;
    }

    @Override
    public void add(AddRentalRequest addRentalRequest) {
        Rental rental=this.modelMapperService.forRequest().map(addRentalRequest,Rental.class);
        this.rentalRepository.save(rental);
    }

    @Override
    public void update(UpdateRentalRequest updateRentalRequest) {
        Rental rental =this.modelMapperService.forRequest().map(updateRentalRequest,Rental.class);
        rentalRepository.save(rental);
    }

    @Override
    public void delete(int id) {
        rentalRepository.deleteById(id);
    }
}
