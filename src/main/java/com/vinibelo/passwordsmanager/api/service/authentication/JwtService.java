package com.vinibelo.passwordsmanager.api.service.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant expiresAt = now.plusSeconds(3600);

        String scopes = "";
        JwtClaimsSet claims = null;
        try {
            scopes = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            claims = JwtClaimsSet.builder()
                    .issuer("spring-security-jwt")
                    .issuedAt(now)
                    .expiresAt(expiresAt)
                    .subject(authentication.getName())
                    .claim("scopes", scopes)
                    .build();
        } catch (Exception e) {
            return null;
        }

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
