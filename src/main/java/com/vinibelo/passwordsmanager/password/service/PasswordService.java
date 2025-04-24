package com.vinibelo.passwordsmanager.password.service;

import com.vinibelo.passwordsmanager.password.data.Password;
import com.vinibelo.passwordsmanager.password.repository.PasswordRepository;

public class PasswordService {
    private final PasswordRepository passwordRepository;

    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public Password save(String password) {
        Password newPassword = new Password();
        newPassword.setPassword(password);
        return passwordRepository.save(newPassword);
    }
}
