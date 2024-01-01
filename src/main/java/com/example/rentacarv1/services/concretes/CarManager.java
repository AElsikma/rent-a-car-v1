package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.Car;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.CarRepository;
import com.example.rentacarv1.services.abstracts.CarService;
import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetCarListResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;
import com.example.rentacarv1.services.rules.CarBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;

    @Override
    public DataResult<List<GetCarListResponse>> getAll() {
      List<Car> cars= carRepository.findAll();
      List<GetCarListResponse> carListResponses=cars.stream()
              .map(car -> this.modelMapperService.forResponse()
                      .map(car,GetCarListResponse.class)).collect(Collectors.toList());
      return new SuccessDataResult<List<GetCarListResponse>>(carListResponses,"Cars Listed");
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
