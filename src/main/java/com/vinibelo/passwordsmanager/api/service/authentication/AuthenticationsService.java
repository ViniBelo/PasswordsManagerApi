package com.vinibelo.passwordsmanager.api.service.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationsService {
    private final JwtService jwtService;

    public AuthenticationsService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}
