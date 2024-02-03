package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.core.config.cache.RedisCacheManager;
import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.core.utilities.results.SuccessDataResult;
import com.example.rentacarv1.core.utilities.results.SuccessResult;
import com.example.rentacarv1.entities.User;
import com.example.rentacarv1.core.utilities.mappers.ModelMapperService;
import com.example.rentacarv1.repositories.UserRepository;
import com.example.rentacarv1.services.abstracts.RoleService;
import com.example.rentacarv1.services.abstracts.UserService;
import com.example.rentacarv1.services.constants.baseMessage.BaseMessages;
import com.example.rentacarv1.services.dtos.requests.user.AddUserRequest;
import com.example.rentacarv1.services.dtos.requests.user.UpdateUserRequest;
import com.example.rentacarv1.services.dtos.responses.user.GetUserListResponse;
import com.example.rentacarv1.services.dtos.responses.user.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private RedisCacheManager redisCacheManager;

    @Override
    public DataResult<List<GetUserListResponse>> getAll() {
        List<GetUserListResponse> userListResponses = (List<GetUserListResponse>) redisCacheManager.getCachedData("userListCache", "getUsersAndCache");
        if (userListResponses == null) {
            userListResponses = getUsersAndCache();
            redisCacheManager.cacheData("userListCache", "getUsersAndCache", userListResponses);
        }

        return new SuccessDataResult<>(userListResponses, BaseMessages.GET_ALL.getMessage(),HttpStatus.OK);
    }
    public List<GetUserListResponse> getUsersAndCache() {
        List<User> users = userRepository.findAll();
        List<GetUserListResponse> userListResponses = users.stream()
                .map(user -> modelMapperService.forResponse().map(user, GetUserListResponse.class))
                .collect(Collectors.toList());
        return userListResponses;
    }

    @Override
    public DataResult<GetUserResponse> getById(int id) {
        User user = userRepository.findById(id).orElseThrow();
        GetUserResponse getUserResponse=this.modelMapperService.forResponse().map(user,GetUserResponse.class);
        return new SuccessDataResult<GetUserResponse>(getUserResponse, BaseMessages.GET.getMessage(), HttpStatus.OK);
    }

    @Override
    public Result add(AddUserRequest addUserRequest) {
        User user = User.builder()
                .name(addUserRequest.getName())
                .surname(addUserRequest.getSurname())
                .gsm(addUserRequest.getGsm())
                .email(addUserRequest.getEmail())
                .authorities(
                        addUserRequest.getRoles().stream()
                                .map(addRoleUserRequest -> roleService.findByName(addRoleUserRequest.getName()))
                                .collect(Collectors.toSet())
                )
                .password(passwordEncoder.encode(addUserRequest.getPassword()))
                .build();

        userRepository.save(user);
        redisCacheManager.cacheData("userListCache", "getUsersAndCache", null);
        return new SuccessResult( HttpStatus.CREATED, BaseMessages.ADD.getMessage());
    }

    @Override
    public Result update(UpdateUserRequest updateUserRequest) {
        User user =this.modelMapperService.forRequest().map(updateUserRequest,User.class);
        userRepository.save(user);
        redisCacheManager.cacheData("userListCache", "getUsersAndCache", null);
        return new SuccessResult( HttpStatus.OK, BaseMessages.UPDATE.getMessage());
    }

    @Override
    public Result delete(int id) {

        userRepository.deleteById(id);
        redisCacheManager.cacheData("userListCache", "getUsersAndCache", null);
        return new SuccessResult( HttpStatus.OK, BaseMessages.DELETE.getMessage());
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
