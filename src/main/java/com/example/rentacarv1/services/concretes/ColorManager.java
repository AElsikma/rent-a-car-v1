package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.Entities.Car;
import com.example.rentacarv1.Entities.Color;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.ColorRepository;
import com.example.rentacarv1.services.abstracts.ColorService;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ColorManager implements ColorService {

    private ColorRepository colorRepository;
    private ModelMapperService modelMapperService;
    @Override
    public List<GetColorListResponse> getAll() {
        List<Color> colors=colorRepository.findAll();
        List<GetColorListResponse> colorListResponse=colors.stream().map(color -> this.modelMapperService.forResponse()
                .map(color,GetColorListResponse.class)).collect(Collectors.toList());

        return colorListResponse;
    }

    @Override
    public GetColorResponse getById(int id) {
        Color color = colorRepository.findById(id).orElseThrow();
        GetColorResponse getColorResponse=this.modelMapperService.forRequest().map(color,GetColorResponse.class);
        return getColorResponse;
    }

    @Override
    public void add(AddColorRequest addColorRequest) {
        Color color=this.modelMapperService.forRequest().map(addColorRequest,Color.class);
        this.colorRepository.save(color);
    }

    @Override
    public void update(UpdateColorRequest updateColorRequest) {
        Color color =this.modelMapperService.forRequest().map(updateColorRequest,Color.class);
        colorRepository.save(color);
    }

    @Override
    public void delete(int id) {
        colorRepository.deleteById(id);
    }
}
