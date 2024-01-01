package com.example.rentacarv1.controllers;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.abstracts.ColorService;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@AllArgsConstructor
public class ColorsController {
    private ColorService colorService;

    @GetMapping("/getAll")
    public DataResult<List<GetColorListResponse>> getAll(){
        return this.colorService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetColorResponse> getById(@PathVariable int id){
        return this.colorService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Result add(@RequestBody @Valid() AddColorRequest addColorRequest){

        return this.colorService.add(addColorRequest);

    }


    @PutMapping("/update")
    public Result update( @RequestBody @Valid() UpdateColorRequest updateColorRequest){
        return this.colorService.update(updateColorRequest);
    }

    @DeleteMapping("/{id}")
    public Result delete( @PathVariable int id){

        return this.colorService.delete(id);
    }
}
