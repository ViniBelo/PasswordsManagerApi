package com.vinibelo.passwordsmanager.api.controller.authentication;

import com.vinibelo.passwordsmanager.api.controller.authentication.dto.AuthenticationBodyDto;
import com.vinibelo.passwordsmanager.api.service.authentication.AuthenticationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authenticate")
public class AuthenticationController {
    private final AuthenticationsService authenticationsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationsService authenticationsService, AuthenticationManager authenticationManager) {
        this.authenticationsService = authenticationsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping()
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationBodyDto authenticationBodyDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationBodyDto.username(),
                            authenticationBodyDto.password()
                    )
            );

            String jwt = authenticationsService.authenticate(authentication);

            return ResponseEntity.ok().header("Authorization", jwt).build();
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body("Username or password is incorrect");
        }
    }
}
