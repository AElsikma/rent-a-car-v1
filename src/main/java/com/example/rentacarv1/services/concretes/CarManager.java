package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Car;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.CarRepository;
import com.example.rentacarv1.services.abstracts.CarService;
import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetCarListResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;
import com.example.rentacarv1.services.rules.CarBusinessRules;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class CarManager implements CarService {

    public static final String HASH_KEY = "CarList";
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;
    private RedisTemplate<String,Object> template;



    @Cacheable(value = "carListCache", key = "#root.methodName")
    public DataResult<List<GetCarListResponse>> getAll() {

        List<Car> cars = carRepository.findAll();

        List<GetCarListResponse> carListResponses = cars.stream()
                .map(car -> modelMapperService.forResponse().map(car, GetCarListResponse.class))
                .collect(Collectors.toList());
        try {
            String carListResponsesString = new ObjectMapper().writeValueAsString(carListResponses);
            template.opsForHash().put("carListCache", "getAll", carListResponsesString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Json processing exception: " + e.getMessage());
        }

        return new SuccessDataResult<>(carListResponses, "Cars listed.");
    }


    @Override
    public DataResult<GetCarResponse> getById(int id) {
        Car car=this.carRepository.findById(id).orElseThrow();
        GetCarResponse carResponse=this.modelMapperService.forResponse()
                .map(car, GetCarResponse.class);
        return new SuccessDataResult<GetCarResponse>(carResponse,"Car Listed") ;

    }

    @Override
    public Result add(AddCarRequest addCarRequest) {

        addCarRequest.setPlate(carBusinessRules.plateValidator(addCarRequest.getPlate()));
        carBusinessRules.existsByPlate(addCarRequest.getPlate());

        Car car =this.modelMapperService.forRequest().map(addCarRequest,Car.class);

        this.carRepository.save(car);
         return new SuccessResult("Car added");
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        updateCarRequest.setPlate(carBusinessRules.plateValidator(updateCarRequest.getPlate()));
        carBusinessRules.existsByPlate(updateCarRequest.getPlate());

       Car car=this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
       this.carRepository.save(car);
       return new SuccessResult("Car updated");

    }

    @Override
    public Result delete(int id) {
         this.carRepository.deleteById(id);
         return new SuccessResult("Car deleted !");
    }
}
