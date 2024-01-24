package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.entities.User;
import com.example.rentacarv1.services.abstracts.AuthService;
import com.example.rentacarv1.services.abstracts.RoleService;
import com.example.rentacarv1.services.dtos.requests.auth.LoginRequest;
import com.example.rentacarv1.services.dtos.requests.user.AddUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final ModelMapperService modelMapperService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public void register(AddUserRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .authorities(
                        request.getRoles().stream()
                                .map(addRoleUserRequest -> roleService.findByName(addRoleUserRequest.getName()))
                                .collect(Collectors.toSet())
                )
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }

    @Override
    public String login(LoginRequest request) {
       /* Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if(authentication.isAuthenticated())
        {
            // jwt oluştur.
            Map<String,Object> claims = new HashMap<>();
            return jwtService.generateToken(request.getEmail(), claims);
        }
        throw new RuntimeException("Bilgiler hatalı");*/
        return "";
    }

}
