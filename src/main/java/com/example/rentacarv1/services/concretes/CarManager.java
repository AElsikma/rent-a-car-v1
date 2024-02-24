package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.configurations.cache.RedisCacheManager;
import com.example.rentacarv1.core.internationalization.MessageService;
import com.example.rentacarv1.core.services.CloudinaryService;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Car;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.entities.enums.CarState;
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
    private final MessageService messageService;
    private final CloudinaryService cloudinaryService;

    @Override
    public DataResult<List<GetCarListResponse>> getAll() {
        List<GetCarListResponse> carListResponses = (List<GetCarListResponse>) redisCacheManager.getCachedData("carListCache", "getCarsAndCache");
        if (carListResponses == null) {
            carListResponses = getCarsAndCache();
            redisCacheManager.cacheData("carListCache", "getCarsAndCache", carListResponses);
        }

        return new SuccessDataResult<>(carListResponses, messageService.getMessage(BaseMessages.GET_ALL),HttpStatus.OK);
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
        carBusinessRules.checkIfCarByIdExists(id);
        Car car=this.carRepository.findById(id).orElseThrow();
        GetCarResponse carResponse=this.modelMapperService.forResponse()
                .map(car, GetCarResponse.class);
        return new SuccessDataResult<>(carResponse, messageService.getMessage(BaseMessages.GET), HttpStatus.OK) ;

    }

    @Override
    public Result add(AddCarRequest addCarRequest) {

        addCarRequest.setPlate(carBusinessRules.plateValidator(addCarRequest.getPlate()));
        carBusinessRules.existsByPlate(addCarRequest.getPlate());
        carBusinessRules.existsByColorId(addCarRequest.getColorId());
        carBusinessRules.existsByModelId(addCarRequest.getModelId());

        Car car =this.modelMapperService.forRequest().map(addCarRequest,Car.class);
        car.setImagePath(cloudinaryService.uploadFile(addCarRequest.getFile()));
        car.setCarState(addCarRequest.getCarState());

        this.carRepository.save(car);
        redisCacheManager.cacheData("carListCache", "getCarsAndCache", null);
         return new SuccessResult(HttpStatus.CREATED, messageService.getMessage(BaseMessages.ADD));
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        updateCarRequest.setPlate(carBusinessRules.plateValidator(updateCarRequest.getPlate()));
        carBusinessRules.checkIfCarByIdExists(updateCarRequest.getId());
        carBusinessRules.existsByPlate(updateCarRequest.getPlate());
        carBusinessRules.existsByColorId(updateCarRequest.getColorId());
        carBusinessRules.existsByModelId(updateCarRequest.getModelId());

       Car car=this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
        car.setImagePath(cloudinaryService.uploadFile(updateCarRequest.getFile()));
        car.setCarState(updateCarRequest.getCarState());
       this.carRepository.save(car);
        redisCacheManager.cacheData("carListCache", "getCarsAndCache", null);
       return new SuccessResult( HttpStatus.OK, messageService.getMessage(BaseMessages.UPDATE));

    }

    @Override
    public Result delete(int id) {
        carBusinessRules.checkIfCarByIdExists(id);
         this.carRepository.deleteById(id);
        redisCacheManager.cacheData("carListCache", "getCarsAndCache", null);
         return new SuccessResult( HttpStatus.OK, messageService.getMessage(BaseMessages.DELETE));
    }

    @Override

    public boolean getCarById(Integer id) {

        return this.carRepository.existsById(id);

    }

    @Override
    public void updateCarState(int carId, CarState newState) {
        DataResult<GetCarResponse> carResponse = getById(carId);
        Car car = modelMapperService.forResponse().map(carResponse.getData(), Car.class);
        car.setCarState(newState);
        carRepository.save(car);
        redisCacheManager.cacheData("carListCache", "getCarsAndCache", null);
    }

    @Override
    public boolean isCarUnderMaintenance(int carId) {
        DataResult<GetCarResponse> carResponse = getById(carId);
        return carResponse.getData().getCarState() == CarState.MAINTENANCE;
    }

    @Override
    public boolean isCarRented(int carId) {
        DataResult<GetCarResponse> carResponse = getById(carId);
        return carResponse.getData().getCarState() == CarState.RENTED;
    }
}
