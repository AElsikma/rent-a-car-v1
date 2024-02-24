package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.configurations.cache.RedisCacheManager;
import com.example.rentacarv1.core.internationalization.MessageService;
import com.example.rentacarv1.core.utilities.results.*;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.entities.concretes.Car;
import com.example.rentacarv1.entities.concretes.Customer;
import com.example.rentacarv1.entities.concretes.Employee;
import com.example.rentacarv1.entities.concretes.Rental;
import com.example.rentacarv1.entities.enums.CarState;
import com.example.rentacarv1.repositories.*;
import com.example.rentacarv1.services.abstracts.CarService;
import com.example.rentacarv1.services.abstracts.RentalService;
import com.example.rentacarv1.services.constants.baseMessage.BaseMessages;
import com.example.rentacarv1.services.constants.rental.RentalMessages;
import com.example.rentacarv1.services.dtos.requests.rental.AddRentalRequest;
import com.example.rentacarv1.services.dtos.requests.rental.UpdateRentalRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;
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
    private RedisCacheManager redisCacheManager;
    private final MessageService messageService;
    private final RentalBusinessRules rentalBusinessRules;
    private final CarService carService;


    @Override
    public DataResult<List<GetRentalListResponse>> getAll() {
        List<GetRentalListResponse> rentalListResponses = (List<GetRentalListResponse>) redisCacheManager.getCachedData("rentalListCache", "getRentalsAndCache");
        if (rentalListResponses == null) {
            rentalListResponses= getRentalsAndCache();
            redisCacheManager.cacheData("rentalListCache", "getRentalsAndCache", rentalListResponses);
        }

        return new SuccessDataResult<>(rentalListResponses,messageService.getMessage(BaseMessages.GET_ALL),HttpStatus.OK);
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
        rentalBusinessRules.checkIfRentalByIdExists(id);
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse getRentalResponse=this.modelMapperService.forResponse().map(rental,GetRentalResponse.class);
        return new SuccessDataResult<GetRentalResponse>(getRentalResponse,messageService.getMessage(BaseMessages.GET) , HttpStatus.OK) ;
    }

    @Override
    public Result add(AddRentalRequest addRentalRequest) {

     rentalBusinessRules.existsByCarId(addRentalRequest.getCarId());
     rentalBusinessRules.existsByCustomerId(addRentalRequest.getCustomerId());
     rentalBusinessRules.existsByEmployeeId(addRentalRequest.getEmployeeId());
     rentalBusinessRules.checkIfRentalByStartDate(addRentalRequest.getStartDate());
     rentalBusinessRules.checkIfRentalByEndDate(addRentalRequest.getEndDate(), addRentalRequest.getStartDate());
     rentalBusinessRules.checkIfRentalByDateValid(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());


     Rental rental=this.modelMapperService.forRequest().map(addRentalRequest,Rental.class);

     DataResult<GetCarResponse> carResponse = carService.getById(addRentalRequest.getCarId());
     rental.setStartKilometer(carResponse.getData().getKilometer());

        double carPrice = rentalBusinessRules.calculateTotalPrice(addRentalRequest.getStartDate(),
                addRentalRequest.getEndDate(), carResponse.getData().getDailyPrice());

        double totalPrice = 0.0;
        totalPrice += carPrice;
        rental.setTotalPrice(totalPrice);
        // Eğer araç kiralandıysa, kiralanamaz durumunda uyarı veya hata dönebilirsiniz.
        if (carService.isCarRented(addRentalRequest.getCarId())) {
            return new ErrorResult(HttpStatus.BAD_REQUEST, RentalMessages.VEHICLE_RENTED);
        }

        this.rentalRepository.save(rental);
        redisCacheManager.cacheData("rentalListCache", "getRentalsAndCache", null);

        // Kiralama işlemi başarılıysa, aracın durumunu RENTED olarak güncelle
        carService.updateCarState(addRentalRequest.getCarId(), CarState.RENTED);

        // Eğer araba bakımdaysa, durumunu Maintenance olarak güncelle
        if (carService.isCarUnderMaintenance(addRentalRequest.getCarId())) {
            carService.updateCarState(addRentalRequest.getCarId(), CarState.MAINTENANCE);
        }
        return new SuccessResult( HttpStatus.CREATED, messageService.getMessage(BaseMessages.ADD));

    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        rentalBusinessRules.checkIfRentalByIdExists(updateRentalRequest.getId());
        rentalBusinessRules.existsByCarId(updateRentalRequest.getCarId());
        rentalBusinessRules.existsByCustomerId(updateRentalRequest.getCustomerId());
        rentalBusinessRules.existsByEmployeeId(updateRentalRequest.getEmployeeId());
        rentalBusinessRules.checkIfRentalByStartDate(updateRentalRequest.getStartDate());
        rentalBusinessRules.checkIfRentalByEndDate(updateRentalRequest.getEndDate(), updateRentalRequest.getStartDate());
        rentalBusinessRules.checkIfRentalByDateValid(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate());

        Rental rental =this.modelMapperService.forRequest().map(updateRentalRequest,Rental.class);

        DataResult<GetCarResponse> carResponse = carService.getById(updateRentalRequest.getCarId());
        rental.setStartKilometer(carResponse.getData().getKilometer());

        rental.setTotalPrice(this.rentalBusinessRules.calculateTotalPrice(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate(), carResponse.getData().getDailyPrice()));
        rentalRepository.save(rental);
        redisCacheManager.cacheData("rentalListCache", "getRentalsAndCache", null);
        return new SuccessResult( HttpStatus.OK, messageService.getMessage(BaseMessages.UPDATE));
    }

    @Override
    public Result delete(int id) {
        rentalBusinessRules.checkIfRentalByIdExists(id);
        Rental deletedRental = rentalRepository.findById(id).orElseThrow();
        carService.updateCarState(deletedRental.getCar().getId(), CarState.AVAILABLE);
        rentalRepository.deleteById(id);
        redisCacheManager.cacheData("rentalListCache", "getRentalsAndCache", null);
        return new SuccessResult( HttpStatus.OK, messageService.getMessage(BaseMessages.DELETE));
    }


}
