package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.configurations.cache.RedisCacheManager;
import com.example.rentacarv1.core.internationalization.MessageService;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.entities.concretes.Color;
import com.example.rentacarv1.repositories.ColorRepository;
import com.example.rentacarv1.services.abstracts.ColorService;
import com.example.rentacarv1.services.dtos.requests.color.AddColorRequest;
import com.example.rentacarv1.services.rules.ColorBusinessRules;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ColorManagerTest
{
    @Mock
    private ColorService colorService;

    @Mock
    private ColorRepository colorRepository;
    @Mock
    private AddColorRequest addColorRequest;

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private ColorBusinessRules colorBusinessRules;
    @Mock
    private RedisCacheManager redisCacheManager;
    @Mock
    private MessageService messageService;


    @Mock
    private List<Color> colors;

    @Mock
    private Color color;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        color = new Color();
        colors = new ArrayList<>();
        addColorRequest = new AddColorRequest();
        colorService= new ColorManager(colorRepository, modelMapperService,redisCacheManager,colorBusinessRules,messageService);
    }
    @AfterEach
    void tearDown()
    {
    }

    @Test
    void colorNameShouldNotDuplicated()
    {
        String color = "Black";
        addColorRequest.setName(color);
        Mockito.when(colorRepository.existsByName(color)).thenReturn(true);
        assertThrows(RuntimeException.class, () -> {
            colorService.add(addColorRequest);
        });
    }

    @Test
    void succesfullyGetAll()
    {
        Mockito.when(colorRepository.findAll()).thenReturn(colors);
        colorService.getAll();
        assert true;

    }

    @Test
    void successfulyGetById()
    {
         addColorRequest.setName("Red");
        Mockito.when(colorRepository.findById(color.getId())).thenReturn(Optional.of(new Color()));
        colorService.getColorById(color.getId());
        assert true;
    }
}