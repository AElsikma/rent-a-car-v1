package com.example.rentacarv1.controllers;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.abstracts.CarService;
import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetCarListResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {
    private CarService carService;

    @GetMapping("/getAll")
    public DataResult<List<GetCarListResponse>> getAll(){
      return this.carService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetCarResponse> getById( @PathVariable int id){
      return this.carService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Result add(@RequestBody @Valid() AddCarRequest addCarRequest){
        return this.carService.add(addCarRequest);

    }


    @PutMapping("/update")
    public Result update( @RequestBody @Valid() UpdateCarRequest updateCarRequest){
       return this.carService.update(updateCarRequest);
    }

    @DeleteMapping("/{id}")
    public Result delete( @PathVariable int id){
        return this.carService.delete(id);
    }








}
