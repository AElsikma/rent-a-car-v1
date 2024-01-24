package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.services.dtos.requests.auth.LoginRequest;
import com.example.rentacarv1.services.dtos.requests.user.AddUserRequest;

public interface AuthService {
    void register(AddUserRequest request);
    String login(LoginRequest request);
}
