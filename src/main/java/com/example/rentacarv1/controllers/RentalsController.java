package com.example.rentacarv1.controllers;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.abstracts.RentalService;
import com.example.rentacarv1.services.dtos.requests.rental.AddRentalRequest;
import com.example.rentacarv1.services.dtos.requests.rental.UpdateRentalRequest;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalListResponse;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
@CrossOrigin
public class RentalsController {
    private RentalService rentalService;

    @GetMapping("/getAll")
    public DataResult<List<GetRentalListResponse>> getAll(){
        return this.rentalService.getAll();
    }

    @GetMapping("/customer/{customerId}")
    public DataResult<List<GetRentalResponse>> getByCustomerId(@PathVariable int customerId){
        return this.rentalService.getByCustomerId(customerId);
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Result add(@RequestBody @Valid() AddRentalRequest addRentalRequest){
        return this.rentalService.add(addRentalRequest);
    }


    @PutMapping("/update")
    public Result update( @RequestBody @Valid() UpdateRentalRequest updateRentalRequest){
       return this.rentalService.update(updateRentalRequest);
    }

    @DeleteMapping("/{id}")
    public Result delete( @PathVariable int id){

        return this.rentalService.delete(id);
    }
}
