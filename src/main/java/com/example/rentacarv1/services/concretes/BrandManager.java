package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.config.cache.RedisCacheManager;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.concretes.Brand;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.BrandRepository;
import com.example.rentacarv1.services.abstracts.BrandService;
import com.example.rentacarv1.services.constants.baseMessage.BaseMessages;
import com.example.rentacarv1.services.dtos.requests.brand.AddBrandRequest;
import com.example.rentacarv1.services.dtos.requests.brand.UpdateBrandRequest;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandListResponse;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandResponse;
import com.example.rentacarv1.services.rules.BrandBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;

    private final BrandBusinessRules brandBusinessRules;
    private RedisCacheManager redisCacheManager;


    @Override
    public DataResult<List<GetBrandListResponse>> getAll() {

        List<GetBrandListResponse> brandListResponses = (List<GetBrandListResponse>) redisCacheManager.getCachedData("brandListCache", "getBrandsAndCache");
        if (brandListResponses == null) {
            brandListResponses = getBrandsAndCache();
            redisCacheManager.cacheData("brandListCache", "getBrandsAndCache", brandListResponses);
        }

        return new SuccessDataResult<>(brandListResponses, BaseMessages.GET_ALL.getMessage(),HttpStatus.OK);
    }

    public List<GetBrandListResponse> getBrandsAndCache() {
        List<Brand> brands = brandRepository.findAll();
        List<GetBrandListResponse> brandListResponses = brands.stream()
                .map(brand -> modelMapperService.forResponse().map(brand, GetBrandListResponse.class))
                .collect(Collectors.toList());
        return brandListResponses;
    }

    @Override
    public DataResult<GetBrandResponse> getById(int id) {

        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse=this.modelMapperService.forResponse().map(brand,GetBrandResponse.class);
        return new SuccessDataResult<GetBrandResponse>(getBrandResponse, BaseMessages.GET.getMessage(),HttpStatus.OK);
    }

    @Override
    public Result add(AddBrandRequest addBrandRequest) {

         brandBusinessRules.checkIfBrandNameExists(addBrandRequest.getName());

        Brand brand=this.modelMapperService.forRequest().map(addBrandRequest,Brand.class);
        this.brandRepository.save(brand);
        redisCacheManager.cacheData("brandListCache", "getBrandsAndCache", null);
        return new SuccessResult(HttpStatus.CREATED, BaseMessages.ADD.getMessage());
    }

    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {
        brandBusinessRules.checkIfBrandNameExists(updateBrandRequest.getName());

        Brand brand =this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
        brandRepository.save(brand);
        redisCacheManager.cacheData("brandListCache", "getBrandsAndCache", null);
        return new SuccessResult( HttpStatus.OK, BaseMessages.UPDATE.getMessage());
    }

    @Override
    public Result delete(int id) {
        brandRepository.deleteById(id);
        redisCacheManager.cacheData("brandListCache", "getBrandsAndCache", null);
        return new SuccessResult( HttpStatus.OK, BaseMessages.DELETE.getMessage());
    }

}
