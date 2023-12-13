package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.Entities.Brand;
import com.example.rentacarv1.Entities.Car;
import com.example.rentacarv1.controllers.BrandsController;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.BrandRepository;
import com.example.rentacarv1.repositories.CarRepository;
import com.example.rentacarv1.services.abstracts.BrandService;
import com.example.rentacarv1.services.dtos.requests.brand.AddBrandRequest;
import com.example.rentacarv1.services.dtos.requests.brand.UpdateBrandRequest;
import com.example.rentacarv1.services.dtos.requests.car.AddCarRequest;
import com.example.rentacarv1.services.dtos.requests.car.UpdateCarRequest;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandListResponse;
import com.example.rentacarv1.services.dtos.responses.brand.GetBrandResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarListResponse;
import com.example.rentacarv1.services.dtos.responses.car.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;


    @Override
    public List<GetBrandListResponse> getAll() {

        List<Brand> brands=brandRepository.findAll();
        List<GetBrandListResponse> brandListResponse=brands.stream().map(brand -> this.modelMapperService.forResponse()
                .map(brand,GetBrandListResponse.class)).collect(Collectors.toList());

        return brandListResponse;
    }

    @Override
    public GetBrandResponse getById(int id) {

        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse=this.modelMapperService.forRequest().map(brand,GetBrandResponse.class);
        return getBrandResponse;
    }

    @Override
    public void add(AddBrandRequest addBrandRequest) {

        Brand brand=this.modelMapperService.forRequest().map(addBrandRequest,Brand.class);
        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand =this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
        brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);
    }

}
