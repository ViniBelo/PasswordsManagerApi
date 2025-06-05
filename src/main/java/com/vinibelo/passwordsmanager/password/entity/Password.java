package com.vinibelo.passwordsmanager.password.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Getter
@Entity(name = "passwords")
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Column(nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt = Timestamp.from(Instant.now());

    @Setter
    @Column(name = "deleted_at")
    private Timestamp deletedAt = null;

    @Setter
    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;
}