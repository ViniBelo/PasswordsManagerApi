package com.vinibelo.passwordsmanager.api.service.password;

import com.vinibelo.passwordsmanager.api.controller.password.exception.UnautorizedException;
import com.vinibelo.passwordsmanager.api.service.utils.TokenManipulator;
import com.vinibelo.passwordsmanager.password.entity.Platform;
import com.vinibelo.passwordsmanager.password.repository.PlatformRepository;
import com.vinibelo.passwordsmanager.user.entity.User;
import com.vinibelo.passwordsmanager.user.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlatformService {
    private final PlatformRepository platformRepository;
    private final UserRepository userRepository;

    public PlatformService(PlatformRepository platformRepository, UserRepository userRepository) {
        this.platformRepository = platformRepository;
        this.userRepository = userRepository;
    }

    public Page<Platform> listPlatformsByUser(String username, int limit, int page) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        Pageable pageable = PageRequest.of(page, limit);
        return platformRepository.findByUserId(user.getId(), pageable);
    }

    public Platform getPlatform(String username, UUID platformId) throws ChangeSetPersister.NotFoundException {
        Platform platform = platformRepository.findById(platformId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        if (!platform.getUser().getUsername().equals(username))
            throw new UnautorizedException("You don't have permission to access this platform");
        return platform;
    }

    public void createPlatform(String username, String nick, Integer renewIn) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(ChangeSetPersister.NotFoundException::new);
        Platform newPlatform = new Platform();
        newPlatform.setNick(nick);
        newPlatform.setRenewIn(renewIn);
        newPlatform.setUser(user);
        platformRepository.save(newPlatform);
    }
}
