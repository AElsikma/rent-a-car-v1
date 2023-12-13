package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.Entities.Car;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.CarRepository;
import com.example.rentacarv1.services.abstracts.CarService;
import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetCarListResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;


    @Override
    public List<GetCarListResponse> getAll() {

        List<Car> cars=carRepository.findAll();
        List<GetCarListResponse> carListResponse=cars.stream().map(car -> this.modelMapperService.forResponse()
                        .map(car,GetCarListResponse.class)).collect(Collectors.toList());

        return carListResponse;
    }

    @Override
    public GetCarResponse getById(int id) {

        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse getCarResponse=this.modelMapperService.forRequest().map(car,GetCarResponse.class);
        return getCarResponse;
    }

    @Override
    public void add(AddCarRequest addCarRequest) {

        Car car=this.modelMapperService.forRequest().map(addCarRequest,Car.class);
        this.carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {
        Car car =this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
        carRepository.save(car);
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }
}
