package com.vinibelo.passwordsmanager.api.service.utils;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class TokenManipulator {
    private final JwtDecoder jwtDecoder;

    TokenManipulator(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    public Jwt getUserId(String token) {
        String splittedToken = token.split(" ")[1];
        return jwtDecoder.decode(splittedToken);
    }
}
