package com.example.rentacarv1.controllers;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.abstracts.ModelService;
import com.example.rentacarv1.services.dtos.requests.model.AddModelRequest;
import com.example.rentacarv1.services.dtos.requests.model.UpdateModelRequest;
import com.example.rentacarv1.services.dtos.responses.model.GetModelListResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
    private ModelService modelService;

    @GetMapping("/getAll")
    public DataResult<List<GetModelListResponse>> getAll(){
        return this.modelService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetModelResponse> getById(@PathVariable int id){
        return this.modelService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Result add(@RequestBody @Valid() AddModelRequest addModelRequest){

        return this.modelService.add(addModelRequest);
    }


    @PutMapping("/update")
    public Result update( @RequestBody @Valid() UpdateModelRequest updateModelRequest){
        return this.modelService.update(updateModelRequest);
    }

    @DeleteMapping("/{id}")
    public Result delete( @PathVariable int id){

        return this.modelService.delete(id);
    }
}
