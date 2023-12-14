package com.example.rentacarv1.controllers;

import com.example.rentacarv1.services.abstracts.RentalService;
import com.example.rentacarv1.services.dtos.requests.rental.AddRentalRequest;
import com.example.rentacarv1.services.dtos.requests.rental.UpdateRentalRequest;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalListResponse;
import com.example.rentacarv1.services.dtos.responses.rental.GetRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
public class RentalsController {
    private RentalService rentalService;

    @GetMapping("/getAll")
    public List<GetRentalListResponse> getAll(){
        return rentalService.getAll();
    }

    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable int id){
        return rentalService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid AddRentalRequest addRentalRequest){
        this.rentalService.add(addRentalRequest);
    }


    @PutMapping("/update")
    public void update( @RequestBody @Valid UpdateRentalRequest updateRentalRequest){
        this.rentalService.update(updateRentalRequest);
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable int id){
        this.rentalService.delete(id);
    }
}
