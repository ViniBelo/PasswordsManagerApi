package com.vinibelo.passwordsmanager.password.entity;

import com.vinibelo.passwordsmanager.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Entity(name = "platforms")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    private String nick;

    @Setter
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @OneToMany(mappedBy = "platform")
    private List<Password> passwords;

    @Setter
    @Column(name = "renew_in")
    private Integer renewIn;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt = Timestamp.from(Instant.now());

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt = Timestamp.from(Instant.now());

    @Setter
    @Column(name = "deleted_at")
    private Timestamp deletedAt = null;
}
