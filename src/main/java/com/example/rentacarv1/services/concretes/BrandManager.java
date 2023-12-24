package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.Brand;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.BrandRepository;
import com.example.rentacarv1.services.abstracts.BrandService;
import com.example.rentacarv1.services.dtos.requests.brand.AddBrandRequest;
import com.example.rentacarv1.services.dtos.requests.brand.UpdateBrandRequest;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandListResponse;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandResponse;
import com.example.rentacarv1.services.rules.BrandBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;

    private final BrandBusinessRules brandBusinessRules;


    @Override
    public DataResult<List<GetBrandListResponse>> getAll() {

        List<Brand> brands=brandRepository.findAll();
        List<GetBrandListResponse> brandListResponse=brands.stream().map(brand -> this.modelMapperService.forResponse()
                .map(brand,GetBrandListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetBrandListResponse>>(brandListResponse,"Brands Listed ");
    }

    @Override
    public DataResult<GetBrandResponse> getById(int id) {

        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse=this.modelMapperService.forResponse().map(brand,GetBrandResponse.class);
        return new SuccessDataResult<GetBrandResponse>(getBrandResponse,"Brand listed");
    }

    @Override
    public Result add(AddBrandRequest addBrandRequest) {

         brandBusinessRules.existsByBrand(addBrandRequest.getName());

        Brand brand=this.modelMapperService.forRequest().map(addBrandRequest,Brand.class);
        this.brandRepository.save(brand);

        return new SuccessResult("Brand added");
    }

    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {
        Brand brand =this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
        brandRepository.save(brand);
        return new SuccessResult("Brand update");
    }

    @Override
    public Result delete(int id) {
        brandRepository.deleteById(id);
        return new SuccessResult("Brand deleted !");
    }

}
