package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.Color;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.ColorRepository;
import com.example.rentacarv1.services.abstracts.ColorService;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import com.example.rentacarv1.services.rules.ColorBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {
    private final ColorRepository colorRepository;
    private final ModelMapperService modelMapperService;

    private final ColorBusinessRules colorBusinessRules;

    @Override
    public DataResult<List<GetColorListResponse>> getAll() {
       List<Color> colors = this.colorRepository.findAll();
       List<GetColorListResponse> getColorListResponses = colors.stream().map
               (color -> this.modelMapperService.forResponse().map(color, GetColorListResponse.class)).
               collect(Collectors.toList());
       return new SuccessDataResult<List<GetColorListResponse>>(getColorListResponses,"Colors listed");
    }

    @Override
    public DataResult<GetColorResponse> getById(int id) {
        Color color  = this.colorRepository.findById(id).orElseThrow();
        GetColorResponse getColorResponse =this.modelMapperService.forResponse()
                .map(color,GetColorResponse.class);
        return new SuccessDataResult<GetColorResponse>(getColorResponse,"Color listed");
    }

    @Override
    public Result add(AddColorRequest addColorRequest) {

        colorBusinessRules.checkIfColorNameExists(addColorRequest.getName());

        Color color = this.modelMapperService.forRequest().map(addColorRequest,Color.class);
        this.colorRepository.save(color);
        return new SuccessResult("Color added");
    }

    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        colorBusinessRules.checkIfColorNameExists(updateColorRequest.getName());

        Color color = this.modelMapperService.forRequest().map(updateColorRequest,Color.class);
        this.colorRepository.save(color);
        return  new SuccessResult("Color updated");
    }

    @Override
    public Result delete(int id) {

        this.colorRepository.deleteById(id);
        return new SuccessResult("Color deleted !");
    }
}
