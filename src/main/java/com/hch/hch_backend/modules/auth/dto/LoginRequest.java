package com.hch.hch_backend.modules.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginRequest {
    private String email;
    private String password;
}
