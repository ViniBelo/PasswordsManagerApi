package com.vinibelo.passwordsmanager.api.service.password;

import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.repository.PasswordRepository;
import org.springframework.stereotype.Service;

@Service
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
