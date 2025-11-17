package com.hch.hch_backend.security.jwt;

import com.hch.hch_backend.modules.users.entity.User;
import com.hch.hch_backend.modules.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    private final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 7; // 7 days

    public RefreshToken createRefreshToken(User user) {

        refreshTokenRepository.deleteByUser(user); // remove old tokens

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public boolean isRefreshTokenValid(RefreshToken token) {
        return token.getExpiryDate().isAfter(Instant.now());
    }
}
