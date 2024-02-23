package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.configurations.cache.RedisCacheManager;
import com.example.rentacarv1.core.internationalization.MessageService;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.entities.User;
import com.example.rentacarv1.repositories.UserRepository;
import com.example.rentacarv1.services.abstracts.UserService;
import com.example.rentacarv1.services.dtos.requests.user.AddUserRequest;
import com.example.rentacarv1.services.dtos.responses.user.GetUserListResponse;
import com.example.rentacarv1.services.rules.UserBusinessRules;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;
    @Mock
    private AddUserRequest addUserRequest;

    @Mock
    private UserBusinessRules userBusinessRules;
    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private RedisCacheManager redisCacheManager;
    @Mock
    private MessageService messageService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private User user;
    @Mock
    private List<User> users;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        userService = new UserManager(userRepository,modelMapperService,passwordEncoder, redisCacheManager, messageService, userBusinessRules);
        addUserRequest = new AddUserRequest();
        users = new ArrayList<>();
        user = new User();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }
    @Test
    void isUserDuplicated()
    {
        String email = "aslib@gmail.com";
        user.setEmail(email);
        addUserRequest.setEmail(email);
        Mockito.when(userRepository.existsByEmail(email)).thenReturn(true);
        try {
            userService.add(addUserRequest);
            assert true;
        } catch (RuntimeException e) {
            assert false;
        }
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getUserById() {
    }


    @Test
    void loadUserByUsername() {
    }
}