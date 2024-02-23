package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.configurations.cache.RedisCacheManager;
import com.example.rentacarv1.core.internationalization.MessageService;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Color;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.ColorRepository;
import com.example.rentacarv1.services.abstracts.ColorService;
import com.example.rentacarv1.services.constants.baseMessage.BaseMessages;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.dtos.requests.color.UpdateColorRequest;
import com.example.rentacarv1.services.dtos.responses.color.GetColorListResponse;
import com.example.rentacarv1.services.dtos.responses.color.GetColorResponse;
import com.example.rentacarv1.services.rules.ColorBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {
    private final ColorRepository colorRepository;
    private final ModelMapperService modelMapperService;
    private RedisCacheManager redisCacheManager;
    private final ColorBusinessRules colorBusinessRules;
    private final MessageService messageService;

    @Override
    public DataResult<List<GetColorListResponse>> getAll() {
        List<GetColorListResponse> colorListResponses = (List<GetColorListResponse>) redisCacheManager.getCachedData("colorListCache", "getColorsAndCache");
        if (colorListResponses == null) {
            colorListResponses = getColorsAndCache();
            redisCacheManager.cacheData("colorListCache", "getColorsAndCache", colorListResponses);
        }
        return new SuccessDataResult<>(colorListResponses, messageService.getMessage(BaseMessages.GET_ALL),HttpStatus.OK);
    }

    public List<GetColorListResponse> getColorsAndCache() {
        List<Color> colors = colorRepository.findAll();
        List<GetColorListResponse> colorListResponses = colors.stream()
                .map(color -> modelMapperService.forResponse().map(color, GetColorListResponse.class))
                .collect(Collectors.toList());
        return colorListResponses;
    }

    @Override
    public DataResult<GetColorResponse> getById(int id) {
        colorBusinessRules.checkIfColorByIdExists(id);
        Color color  = this.colorRepository.findById(id).orElseThrow();
        GetColorResponse getColorResponse =this.modelMapperService.forResponse()
                .map(color,GetColorResponse.class);
        return new SuccessDataResult<GetColorResponse>(getColorResponse, messageService.getMessage(BaseMessages.GET), HttpStatus.OK);
    }

    @Override
    public Result add(AddColorRequest addColorRequest) {

        colorBusinessRules.checkIfColorNameExists(addColorRequest.getName());

        Color color = this.modelMapperService.forRequest().map(addColorRequest,Color.class);
        this.colorRepository.save(color);
        redisCacheManager.cacheData("colorListCache", "getColorsAndCache", null);
        return new SuccessResult( HttpStatus.CREATED, messageService.getMessage(BaseMessages.ADD));
    }

    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        colorBusinessRules.checkIfColorByIdExists(updateColorRequest.getId());
        colorBusinessRules.checkIfColorNameExists(updateColorRequest.getName());

        Color color = this.modelMapperService.forRequest().map(updateColorRequest,Color.class);
        this.colorRepository.save(color);
        redisCacheManager.cacheData("colorListCache", "getColorsAndCache", null);
        return  new SuccessResult( HttpStatus.OK,messageService.getMessage(BaseMessages.UPDATE));
    }

    @Override
    public Result delete(int id) {
        colorBusinessRules.checkIfColorByIdExists(id);
        this.colorRepository.deleteById(id);
        redisCacheManager.cacheData("colorListCache", "getColorsAndCache", null);
        return new SuccessResult( HttpStatus.OK, messageService.getMessage(BaseMessages.DELETE));
    }

    @Override

    public boolean getColorById(Integer id) {

        return this.colorRepository.existsById(id);

    }
}
