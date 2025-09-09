package com.vinibelo.passwordsmanager.api.service.password;

import com.vinibelo.passwordsmanager.api.controller.password.exception.UnautorizedException;
import com.vinibelo.passwordsmanager.api.service.utils.TokenManipulator;
import com.vinibelo.passwordsmanager.password.domain.PasswordGenerator;
import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.entity.Platform;
import com.vinibelo.passwordsmanager.password.repository.PasswordRepository;
import com.vinibelo.passwordsmanager.password.repository.PlatformRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordService {
    private final PasswordRepository passwordRepository;
    private final PlatformRepository platformRepository;

    public PasswordService(PasswordRepository passwordRepository, PlatformRepository platformRepository) {
        this.passwordRepository = passwordRepository;
        this.platformRepository = platformRepository;
    }

    public Password newPassword(String username, UUID platformId) throws ChangeSetPersister.NotFoundException {
        Platform platform = platformRepository.findById(platformId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        if (!platform.getUser().getUsername().equals(username))
            throw new UnautorizedException("You don't have permission to access this platform");
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        Password newPassword = new Password();
        newPassword.setPassword(passwordGenerator.generatePassword());
        newPassword.setPlatform(platform);
        return passwordRepository.save(newPassword);
    }
}
