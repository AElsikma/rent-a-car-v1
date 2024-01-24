package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.dtos.requests.auth.LoginRequest;
import com.example.rentacarv1.services.dtos.requests.user.AddUserRequest;
import com.example.rentacarv1.services.dtos.requests.user.CreateUserRequest;
import com.example.rentacarv1.services.dtos.requests.user.UpdateUserRequest;
import com.example.rentacarv1.services.dtos.responses.user.GetUserListResponse;
import com.example.rentacarv1.services.dtos.responses.user.GetUserResponse;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    DataResult<List<GetUserListResponse>> getAll();
    DataResult<GetUserResponse> getById(int id);
    Result add (AddUserRequest addUserRequest);
    Result update (UpdateUserRequest updateUserRequest);
    Result delete (int id);


}
