package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.config.cache.RedisCacheManager;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Car;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.CarRepository;
import com.example.rentacarv1.services.abstracts.CarService;
import com.example.rentacarv1.services.constants.baseMessage.BaseMessages;
import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetCarListResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;
import com.example.rentacarv1.services.rules.CarBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;
    private RedisCacheManager redisCacheManager;

    @Override
    public DataResult<List<GetCarListResponse>> getAll() {
        List<GetCarListResponse> carListResponses = (List<GetCarListResponse>) redisCacheManager.getCachedData("carListCache", "getCarsAndCache");
        if (carListResponses == null) {
            carListResponses = getCarsAndCache();
            redisCacheManager.cacheData("carListCache", "getCarsAndCache", carListResponses);
        }

        return new SuccessDataResult<>(carListResponses, BaseMessages.GET_ALL.getMessage(),HttpStatus.OK);
    }

    public List<GetCarListResponse> getCarsAndCache() {
        List<Car> cars = carRepository.findAll();
        List<GetCarListResponse> carListResponses = cars.stream()
                .map(car -> modelMapperService.forResponse().map(car, GetCarListResponse.class))
                .collect(Collectors.toList());
        return carListResponses;
    }

        @Override
    public DataResult<GetCarResponse> getById(int id) {
        Car car=this.carRepository.findById(id).orElseThrow();
        GetCarResponse carResponse=this.modelMapperService.forResponse()
                .map(car, GetCarResponse.class);
        return new SuccessDataResult<>(carResponse, BaseMessages.GET.getMessage(), HttpStatus.OK) ;

    }

    @Override
    public Result add(AddCarRequest addCarRequest) {

        addCarRequest.setPlate(carBusinessRules.plateValidator(addCarRequest.getPlate()));
        carBusinessRules.existsByPlate(addCarRequest.getPlate());

        Car car =this.modelMapperService.forRequest().map(addCarRequest,Car.class);

        this.carRepository.save(car);
        redisCacheManager.cacheData("carListCache", "getCarsAndCache", null);
         return new SuccessResult(HttpStatus.CREATED, BaseMessages.ADD.getMessage());
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        updateCarRequest.setPlate(carBusinessRules.plateValidator(updateCarRequest.getPlate()));
        carBusinessRules.existsByPlate(updateCarRequest.getPlate());

       Car car=this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
       this.carRepository.save(car);
        redisCacheManager.cacheData("carListCache", "getCarsAndCache", null);
       return new SuccessResult( HttpStatus.OK, BaseMessages.UPDATE.getMessage());

    }

    @Override
    public Result delete(int id) {
         this.carRepository.deleteById(id);
        redisCacheManager.cacheData("carListCache", "getCarsAndCache", null);
         return new SuccessResult( HttpStatus.OK, BaseMessages.DELETE.getMessage());
    }
}
