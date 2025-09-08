package com.vinibelo.passwordsmanager.api.service.password;

import com.vinibelo.passwordsmanager.api.service.utils.TokenManipulator;
import com.vinibelo.passwordsmanager.password.entity.Platform;
import com.vinibelo.passwordsmanager.password.repository.PlatformRepository;
import com.vinibelo.passwordsmanager.user.entity.User;
import com.vinibelo.passwordsmanager.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlatformService {
    private final PlatformRepository platformRepository;
    private final TokenManipulator tokenManipulator;
    private final UserRepository userRepository;

    public PlatformService(PlatformRepository platformRepository, TokenManipulator tokenManipulator, UserRepository userRepository) {
        this.platformRepository = platformRepository;
        this.tokenManipulator = tokenManipulator;
        this.userRepository = userRepository;
    }

    public Page<Platform> listPlatformsByUser(String username, int limit, int page) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        Pageable pageable = PageRequest.of(page, limit);
        return platformRepository.findByUserId(user.getId(), pageable);
    }

    public Platform getPlatform(String token, UUID platformId) {
        Jwt decodedToken = tokenManipulator.getUserId(token);
        Platform platform = platformRepository.findById(platformId).orElseThrow(RuntimeException::new);
        if (!platform.getUser().getUsername().equals(decodedToken.getSubject()))
            throw new RuntimeException("You don't have permission to access this platform");
        return platform;
    }

    public void createPlatform(String token, String nick, Integer renewIn) {
        Jwt decodedToken = tokenManipulator.getUserId(token);
        User user = userRepository.findByUsername(decodedToken.getSubject()).orElseThrow(RuntimeException::new);
        Platform newPlatform = new Platform();
        newPlatform.setNick(nick);
        newPlatform.setRenewIn(renewIn);
        newPlatform.setUser(user);
        platformRepository.save(newPlatform);
    }
}
