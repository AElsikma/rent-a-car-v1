package com.example.rentacarv1.controllers;

import com.example.rentacarv1.services.abstracts.ColorService;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@AllArgsConstructor
public class ColorsController {
    private ColorService colorService;

    @GetMapping("/getAll")
    public List<GetColorListResponse> getAll(){
        return colorService.getAll();
    }

    @GetMapping("/{id}")
    public GetColorResponse getById(@PathVariable int id){
        return colorService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid AddColorRequest addColorRequest){
        this.colorService.add(addColorRequest);
    }


    @PutMapping("/update")
    public void update( @RequestBody @Valid UpdateColorRequest updateColorRequest){
        this.colorService.update(updateColorRequest);
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable int id){
        this.colorService.delete(id);
    }
}
