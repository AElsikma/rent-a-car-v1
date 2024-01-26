package com.example.rentacarv1.services.abstracts;

import com.example.rentacarv1.entities.concretes.RefreshToken;

public interface RefreshTokenService {

    boolean  isRefreshExpired(RefreshToken token);
}
