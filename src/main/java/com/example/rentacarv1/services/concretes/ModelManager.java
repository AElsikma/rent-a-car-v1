package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.Model;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.ModelRepository;
import com.example.rentacarv1.services.abstracts.ModelService;
import com.example.rentacarv1.services.dtos.requests.model.AddModelRequest;
import com.example.rentacarv1.services.dtos.requests.model.UpdateModelRequest;
import com.example.rentacarv1.services.dtos.responses.model.GetModelListResponse;
import com.example.rentacarv1.services.dtos.responses.model.GetModelResponse;
import com.example.rentacarv1.services.rules.ModelBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapperService modelMapperService;

    private final ModelBusinessRules modelBusinessRules;

    @Override
    public DataResult<List<GetModelListResponse>> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetModelListResponse> getModelListResponses = models.stream().map
                (model -> this.modelMapperService.forResponse().map(model, GetModelListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<GetModelListResponse>>(getModelListResponses,"Models listed");
    }

    @Override
    public DataResult<GetModelResponse> getById(int id) {
        Model model = modelRepository.findById(id).orElseThrow();
        GetModelResponse getModelResponse = this.modelMapperService.forResponse().map(model,GetModelResponse.class);
        return new SuccessDataResult<GetModelResponse>(getModelResponse,"Model listed");
    }

    @Override
    public Result add(AddModelRequest addModelRequest) {

        modelBusinessRules.checkIfModelNameExists(addModelRequest.getName());

        Model model = this.modelMapperService.forRequest().map(addModelRequest,Model.class);
        this.modelRepository.save(model);
        return new SuccessResult("Model added");
    }



    @Override
    public Result update(UpdateModelRequest updateModelRequest) {
        modelBusinessRules.checkIfModelNameExists(updateModelRequest.getName());

        Model model = this.modelMapperService.forRequest().map(updateModelRequest,Model.class);
        this.modelRepository.save(model);
        return new SuccessResult("Model updated");
    }

    @Override
    public Result delete(int id) {

        this.modelRepository.deleteById(id);
        return new SuccessResult("Model deleted !");
    }
}
