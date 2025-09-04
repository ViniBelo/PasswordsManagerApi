package com.vinibelo.passwordsmanager.api.service.password;

import com.vinibelo.passwordsmanager.api.service.utils.TokenManipulator;
import com.vinibelo.passwordsmanager.password.domain.PasswordGenerator;
import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.entity.Platform;
import com.vinibelo.passwordsmanager.password.repository.PasswordRepository;
import com.vinibelo.passwordsmanager.password.repository.PlatformRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordService {
    private final PasswordRepository passwordRepository;
    private final PlatformRepository platformRepository;
    private final TokenManipulator tokenManipulator;

    public PasswordService(PasswordRepository passwordRepository, PlatformRepository platformRepository, TokenManipulator tokenManipulator) {
        this.passwordRepository = passwordRepository;
        this.platformRepository = platformRepository;
        this.tokenManipulator = tokenManipulator;
    }

    public Password newPassword(String token, UUID platformId) {
        Jwt decodedToken = tokenManipulator.getUserId(token);
        Platform platform = platformRepository.findById(platformId).orElseThrow(RuntimeException::new);
        if (!platform.getUser().getUsername().equals(decodedToken.getSubject()))
            throw new RuntimeException("You don't have permission to access this platform");
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        Password newPassword = new Password();
        newPassword.setPassword(passwordGenerator.generatePassword());
        newPassword.setPlatform(platform);
        return passwordRepository.save(newPassword);
    }
}
