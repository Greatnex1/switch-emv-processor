package com.ubagroup.switchemv.service;

import com.ubagroup.switchemv.model.AppUser;
import com.ubagroup.switchemv.model.RefreshToken;
import com.ubagroup.switchemv.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(AppUser user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
}
