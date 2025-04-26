package com.vinibelo.passwordsmanager.password.repository;

import com.vinibelo.passwordsmanager.password.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PasswordRepository extends JpaRepository<Password, UUID> {}
