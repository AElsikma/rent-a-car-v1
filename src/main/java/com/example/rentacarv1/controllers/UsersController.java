package com.example.rentacarv1.controllers;

import com.example.rentacarv1.core.services.JwtService;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.abstracts.UserService;
import com.example.rentacarv1.services.dtos.requests.auth.LoginRequest;
import com.example.rentacarv1.services.dtos.requests.user.AddUserRequest;
import com.example.rentacarv1.services.dtos.requests.user.UpdateUserRequest;
import com.example.rentacarv1.services.dtos.requests.user.UserAuthenticationRequest;
import com.example.rentacarv1.services.dtos.responses.user.GetUserListResponse;
import com.example.rentacarv1.services.dtos.responses.user.GetUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersController {
    private UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @GetMapping("/getAll")
    public DataResult<List<GetUserListResponse>> getAll(){
        return this.userService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetUserResponse> getById(@PathVariable int id){
        return this.userService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Result add(@RequestBody @Valid() AddUserRequest addUserRequest){

        return this.userService.add(addUserRequest);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserAuthenticationRequest userAuthenticationRequest){
        userService.register(userAuthenticationRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if(authentication.isAuthenticated())
        {

            Map<String,Object> claims = new HashMap<>();
            return jwtService.generateToken(request.getEmail(), claims);
        }
        throw new RuntimeException("Bilgiler hatalı");
    }

    @PutMapping("/update")
    public Result update( @RequestBody @Valid() UpdateUserRequest updateUserRequest){
        return this.userService.update(updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public Result delete( @PathVariable int id){

        return this.userService.delete(id);
    }
}
