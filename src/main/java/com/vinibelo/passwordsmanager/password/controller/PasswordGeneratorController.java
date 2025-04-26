package com.vinibelo.passwordsmanager.password.controller;

import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.domain.PasswordGenerator;
import com.vinibelo.passwordsmanager.password.repository.PasswordRepository;
import com.vinibelo.passwordsmanager.password.service.PasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController()
@RequestMapping("passwords")
public class PasswordGeneratorController {
    PasswordService passwordService;

    public PasswordGeneratorController(PasswordRepository passwordRepository) {
        this.passwordService = new PasswordService(passwordRepository);
    }

    @PostMapping()
    public ResponseEntity<Password> generatePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        Password password = passwordService.save(passwordGenerator.generatePassword());
        String uri = "/passwords/" + password.getId();
        return ResponseEntity.created(URI.create(uri)).body(password);
    }
}
