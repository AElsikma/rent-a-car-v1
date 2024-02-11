package com.example.rentacarv1.controllers;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.abstracts.BrandService;
import com.example.rentacarv1.services.dtos.requests.brand.AddBrandRequest;
import com.example.rentacarv1.services.dtos.requests.brand.UpdateBrandRequest;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandListResponse;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
@CrossOrigin
public class BrandsController {
    private BrandService brandService;

    @GetMapping("/getAll")
    public DataResult<List<GetBrandListResponse>> getAll(){

        return this.brandService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetBrandResponse> getById(@PathVariable int id){
        return this.brandService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Result add(@RequestBody @Valid() AddBrandRequest addBrandRequest) {
       return this.brandService.add(addBrandRequest);
    }


    @PutMapping("/update")
    public Result update( @RequestBody @Valid() UpdateBrandRequest updateBrandRequest){
        return this.brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/{id}")
    public Result delete( @PathVariable int id){
        return this.brandService.delete(id);
    }

}
