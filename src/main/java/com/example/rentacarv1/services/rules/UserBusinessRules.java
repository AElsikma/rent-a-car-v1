package com.example.rentacarv1.services.rules;

import com.example.rentacarv1.core.utilities.exceptions.types.BusinessException;
import com.example.rentacarv1.repositories.UserRepository;
import com.example.rentacarv1.services.constants.user.UserMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;



    public void checkIfUserByEmailExists(String email) {
        if (this.userRepository.existsByEmail(email)) {
            throw new BusinessException(UserMessages.EMAIL_ALREADY_EXISTS);
        }
    }

    public void checkIfUserByGsmExists(String gsm) {
        if (this.userRepository.existsByGsm(gsm)) {
            throw new BusinessException(UserMessages.PHONE_ALREADY_EXISTS);
        }
    }

    public void checkIfUserByIdExists(Integer id) {
        if (!this.userRepository.existsById(id)) {
            throw new BusinessException(UserMessages.ID_NOT_FOUND);
        }
    }
}
