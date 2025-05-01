package com.vinibelo.passwordsmanager.password.entity;

import com.vinibelo.passwordsmanager.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}