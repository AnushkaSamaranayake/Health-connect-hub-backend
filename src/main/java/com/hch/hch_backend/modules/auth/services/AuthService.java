package com.hch.hch_backend.modules.auth.services;

import com.hch.hch_backend.modules.auth.dto.AuthResponse;
import com.hch.hch_backend.modules.auth.dto.LoginRequest;
import com.hch.hch_backend.modules.auth.dto.RegisterRequest;
import com.hch.hch_backend.modules.users.entity.Role;
import com.hch.hch_backend.modules.users.entity.User;
import com.hch.hch_backend.modules.users.entity.UserRole;
import com.hch.hch_backend.modules.users.repository.RoleRepository;
import com.hch.hch_backend.modules.users.repository.UserRoleRepository;
import com.hch.hch_backend.security.jwt.RefreshToken;
import com.hch.hch_backend.modules.users.repository.UserRepository;

import com.hch.hch_backend.security.jwt.JwtService;
import com.hch.hch_backend.security.jwt.RefreshToken;
import com.hch.hch_backend.security.jwt.RefreshTokenService;


import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    
    // Registration method
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status("ACTIVE")
                .build();

        userRepository.save(user);

        // Assign default role to the user
        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Invalid Role"));

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();

        userRoleRepository.save(userRole);

        return AuthResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(role.getName())
                .build();
    }

    // Login method
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        // Generate JWT
        String accessToken = jwtService.generateToken(user.getEmail());

        // Create Refresh Token
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        // Fetch role
        UserRole userRole = userRoleRepository.findAll()
                .stream()
                .filter(ur -> ur.getUser().getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not assigned"));

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .userId(user.getId())
                .email(user.getEmail())
                .role(userRole.getRole().getName())
                .build();
    }

}