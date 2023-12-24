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
import com.example.rentacarv1.services.dtos.responses.car.GetByIdCarResponse;
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
    public DataResult<List<GetCarResponse>> getAll() {
      List<Car> cars= carRepository.findAll();
      List<GetCarResponse> carResponses=cars.stream()
              .map(car -> this.modelMapperService.forResponse()
                      .map(car,GetCarResponse.class)).collect(Collectors.toList());
      return new SuccessDataResult<List<GetCarResponse>>(carResponses,"Cars Listed");
    }

    @Override
    public DataResult<GetByIdCarResponse> getById(int id) {
        Car car=this.carRepository.findById(id).orElseThrow();
        GetByIdCarResponse byIdCarResponse=this.modelMapperService.forResponse()
                .map(car, GetByIdCarResponse.class);
        return new SuccessDataResult<GetByIdCarResponse>(byIdCarResponse,"Car Listed") ;

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
