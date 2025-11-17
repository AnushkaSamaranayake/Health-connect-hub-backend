package com.hch.hch_backend.modules.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegisterRequest {
    
    private String email;
    private String password;

    private String firstName;
    private String lastName;

    private String role;
}
