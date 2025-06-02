package com.vinibelo.passwordsmanager.api.service.password;

import com.vinibelo.passwordsmanager.api.service.utils.TokenManipulator;
import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.entity.Platform;
import com.vinibelo.passwordsmanager.password.repository.PasswordRepository;
import com.vinibelo.passwordsmanager.user.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordService {
    private final PasswordRepository passwordRepository;
    private final UserRepository userRepository;
    private final TokenManipulator tokenManipulator;

    public PasswordService(PasswordRepository passwordRepository, UserRepository userRepository, TokenManipulator tokenManipulator) {
        this.passwordRepository = passwordRepository;
        this.userRepository = userRepository;
        this.tokenManipulator = tokenManipulator;
    }

    public Password save(String nick, Integer renewIn, String password, String token) {
        Platform newPlatform = new Platform();
        Password newPassword = new Password();
        newPlatform.setNick(nick);
        newPlatform.setRenewIn(renewIn);
        newPassword.setPassword(password);
        Jwt decodedToken = tokenManipulator.getUserId(token);
        userRepository.findByUsername(decodedToken.getSubject())
                .ifPresent(newPlatform::setUser);
        newPassword.setPlatform(newPlatform);
        return passwordRepository.save(newPassword);
    }

    public Password searchPasswordById(UUID id) {
        return passwordRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
