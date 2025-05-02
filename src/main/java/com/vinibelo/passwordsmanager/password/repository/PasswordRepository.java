package com.vinibelo.passwordsmanager.password.repository;

import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordRepository extends JpaRepository<Password, UUID> {
    List<Password> searchPasswordByUserId(UUID user_id);
}
