package com.example.rentacarv1.services.concretes;

import com.example.rentacarv1.entities.concretes.RefreshToken;
import com.example.rentacarv1.repositories.RefreshTokenRepository;
import com.example.rentacarv1.services.abstracts.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class RefreshTokenManager implements RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public boolean isRefreshExpired(RefreshToken token) {
        return token.getExpiryDate().before(new Date());
    }

}
