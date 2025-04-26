package com.vinibelo.passwordsmanager.api.controller;

import com.vinibelo.passwordsmanager.api.service.authentication.AuthenticationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authenticate")
public class AuthenticationController {
    private final AuthenticationsService authenticationsService;

    public AuthenticationController(AuthenticationsService authenticationsService) {
        this.authenticationsService = authenticationsService;
    }

    @PostMapping()
    public ResponseEntity<String> authenticate(Authentication authentication) {
        String response = authenticationsService.authenticate(authentication);
        if (response == null) {
            return ResponseEntity.badRequest().body("Username or password is incorrect");
        }
        return ResponseEntity.ok(response);
    }
}
