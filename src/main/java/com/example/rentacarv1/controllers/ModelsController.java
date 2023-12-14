package com.example.rentacarv1.controllers;

import com.example.rentacarv1.services.abstracts.ColorService;
import com.example.rentacarv1.services.abstracts.ModelService;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.requests.model.AddModelRequest;
import com.example.rentacarv1.services.dtos.requests.model.UpdateModelRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import com.example.rentacarv1.services.dtos.responses.employee.GetEmployeeListResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelListResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelsController {
    private ModelService modelService;

    @GetMapping("/getAll")
    public List<GetModelListResponse> getAll(){
        return modelService.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable int id){
        return modelService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid AddModelRequest addModelRequest){
        this.modelService.add(addModelRequest);
    }


    @PutMapping("/update")
    public void update( @RequestBody @Valid UpdateModelRequest updateModelRequest){
        this.modelService.update(updateModelRequest);
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable int id){
        this.modelService.delete(id);
    }
}
