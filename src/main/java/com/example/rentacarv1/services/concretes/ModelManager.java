package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.config.cache.RedisCacheManager;
import com.example.rentacarv1.core.internationalization.MessageService;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Model;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.ModelRepository;
import com.example.rentacarv1.services.abstracts.ModelService;
import com.example.rentacarv1.services.constants.baseMessage.BaseMessages;
import com.example.rentacarv1.services.dtos.requests.model.AddModelRequest;
import com.example.rentacarv1.services.dtos.requests.model.UpdateModelRequest;
import com.example.rentacarv1.services.dtos.responses.model.GetModelListResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelResponse;
import com.example.rentacarv1.services.rules.ModelBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapperService modelMapperService;
    private final ModelBusinessRules modelBusinessRules;
    private RedisCacheManager redisCacheManager;
    private final MessageService messageService;

    @Override
    public DataResult<List<GetModelListResponse>> getAll() {
        List<GetModelListResponse> modelListResponses = (List<GetModelListResponse>) redisCacheManager.getCachedData("modelListCache", "getModelsAndCache");
        if (modelListResponses == null) {
            modelListResponses = getModelsAndCache();
            redisCacheManager.cacheData("modelListCache", "getModelsAndCache", modelListResponses);
        }

        return new SuccessDataResult<>(modelListResponses, messageService.getMessage(BaseMessages.GET_ALL),HttpStatus.OK);
    }

    public List<GetModelListResponse> getModelsAndCache() {
        List<Model> models = modelRepository.findAll();
        List<GetModelListResponse> modelListResponses = models.stream()
                .map(model -> modelMapperService.forResponse().map(model, GetModelListResponse.class))
                .collect(Collectors.toList());
        return modelListResponses;
    }

    @Override
    public DataResult<GetModelResponse> getById(int id) {
        Model model = modelRepository.findById(id).orElseThrow();
        GetModelResponse getModelResponse = this.modelMapperService.forResponse().map(model,GetModelResponse.class);
        return new SuccessDataResult<GetModelResponse>(getModelResponse,messageService.getMessage(BaseMessages.GET) , HttpStatus.OK);
    }

    @Override
    public DataResult<List<GetModelListResponse>> getByBrandId(int id) {
        List<Model> models = modelRepository.findByBrandId(id);
        List<GetModelListResponse> modelListResponses = models.stream()
                .map(model -> modelMapperService.forResponse().map(model, GetModelListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(modelListResponses,"Model listed",HttpStatus.OK);
    }

    @Override
    public Result add(AddModelRequest addModelRequest) {

        modelBusinessRules.checkIfModelNameExists(addModelRequest.getName());

        Model model = this.modelMapperService.forRequest().map(addModelRequest,Model.class);
        this.modelRepository.save(model);
        redisCacheManager.cacheData("modelListCache", "getModelsAndCache", null);
        return new SuccessResult( HttpStatus.CREATED,messageService.getMessage(BaseMessages.ADD) );
    }



    @Override
    public Result update(UpdateModelRequest updateModelRequest) {
        modelBusinessRules.checkIfModelNameExists(updateModelRequest.getName());

        Model model = this.modelMapperService.forRequest().map(updateModelRequest,Model.class);
        this.modelRepository.save(model);
        redisCacheManager.cacheData("modelListCache", "getModelsAndCache", null);
        return new SuccessResult( HttpStatus.OK, messageService.getMessage(BaseMessages.UPDATE));
    }

    @Override
    public Result delete(int id) {

        this.modelRepository.deleteById(id);
        redisCacheManager.cacheData("modelListCache", "getModelsAndCache", null);
        return new SuccessResult( HttpStatus.OK, messageService.getMessage(BaseMessages.DELETE));
    }
}
