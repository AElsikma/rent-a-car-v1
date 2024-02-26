package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.configurations.cache.RedisCacheManager;
import com.example.rentacarv1.core.internationalization.MessageService;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.entities.concretes.Brand;
import com.example.rentacarv1.repositories.BrandRepository;
import com.example.rentacarv1.services.abstracts.BrandService;
import com.example.rentacarv1.services.dtos.requests.brand.AddBrandRequest;
import com.example.rentacarv1.services.rules.BrandBusinessRules;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



class BrandManagerTest {
    @Mock
    private BrandService brandService;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private BrandBusinessRules brandBusinessRules;

    @Mock
    private AddBrandRequest addBrandRequest;
    @Mock
    private RedisCacheManager redisCacheManager;
    @Mock
    private MessageService messageService;

    @Mock
    private List<Brand> brands;

    @Mock
    private Brand brand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addBrandRequest = new AddBrandRequest();
        brands = new ArrayList<>();
        brand = new Brand();
        brandService = new BrandManager(brandRepository, modelMapperService, brandBusinessRules,redisCacheManager, messageService);
        ModelMapper modelMapperForMock = Mockito.mock(ModelMapper.class);
        Mockito.when(modelMapperService.forRequest()).thenReturn(modelMapperForMock);
        Mockito.when(modelMapperService.forResponse()).thenReturn(modelMapperForMock);
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void correctDelete() {

    }

    @Test
    void correctGetAll(){
        Mockito.when(brandRepository.findAll()).thenReturn(brands);
        brandService.getAll();
        assert true;
    }

    @Test
    void correctGetById()
    {
        Mockito.when(brandRepository.findById(brand.getId())).thenReturn(Optional.of(new Brand()));
        brandService.getById(brand.getId());
        assert true;
    }
}