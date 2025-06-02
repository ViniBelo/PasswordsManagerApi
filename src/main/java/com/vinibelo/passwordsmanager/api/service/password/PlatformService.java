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

@Service
public class PlatformService {
    private final PlatformRepository platformRepository;
    private final TokenManipulator tokenManipulator;
    private final UserRepository userRepository;

    PlatformService(PlatformRepository platformRepository, TokenManipulator tokenManipulator, UserRepository userRepository) {
        this.platformRepository = platformRepository;
        this.tokenManipulator = tokenManipulator;
        this.userRepository = userRepository;
    }

    public Page<Platform> listPlatformsByUser(String token, int limit, int page) {
        Jwt decodedToken = tokenManipulator.getUserId(token);
        User user = userRepository.findByUsername(decodedToken.getSubject()).orElseThrow(RuntimeException::new);
        Pageable pageable = PageRequest.of(page, limit);
        return platformRepository.listPlatformsByUserId(user.getId(), pageable);
    }
}
