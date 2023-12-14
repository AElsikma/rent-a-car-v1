package com.example.rentacarv1.controllers;

import com.example.rentacarv1.services.abstracts.CarService;
import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.car.GetByIdCarResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {
    private CarService carService;

    @GetMapping("/getAll")
    public List<GetCarResponse> getAll(){
      return carService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdCarResponse getById( @PathVariable int id){
      return carService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid AddCarRequest addCarRequest){
         this.carService.add(addCarRequest);
    }


    @PutMapping("/update")
    public void update( @RequestBody @Valid UpdateCarRequest updateCarRequest){
        this.carService.update(updateCarRequest);
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable int id){
         this.carService.delete(id);
    }








}
