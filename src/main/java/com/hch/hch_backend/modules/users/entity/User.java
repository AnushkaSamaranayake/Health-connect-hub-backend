package com.hch.hch_backend.modules.users.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    @Column(nullable = false) // cannot be null
    private String email;

    @Column(nullable = false) // cannot be null
    private String passwordHash;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(nullable = false) // cannot be null
    private String status; // ACTIVE, INACTIVE, SUSPENDED
}
