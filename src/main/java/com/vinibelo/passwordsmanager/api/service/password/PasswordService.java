package com.vinibelo.passwordsmanager.api.service.password;

import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.repository.PasswordRepository;
import com.vinibelo.passwordsmanager.user.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private final PasswordRepository passwordRepository;
    private final UserRepository userRepository;
    private final JwtDecoder jwtDecoder;

    public PasswordService(PasswordRepository passwordRepository, UserRepository userRepository, JwtDecoder jwtDecoder) {
        this.passwordRepository = passwordRepository;
        this.userRepository = userRepository;
        this.jwtDecoder = jwtDecoder;
    }

    public Password save(String nick, String password, String token) {
        Password newPassword = new Password();
        newPassword.setNick(nick);
        newPassword.setPassword(password);
        Jwt decodedToken = decodeToken(token);
        userRepository.findByUsername(decodedToken.getSubject())
                .ifPresent(newPassword::setUser);
        return passwordRepository.save(newPassword);
    }

    private Jwt decodeToken(String token) {
        String splittedToken = token.split(" ")[1];
        return jwtDecoder.decode(splittedToken);
    }
}
