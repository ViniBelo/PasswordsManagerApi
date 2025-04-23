package com.vinibelo.passwordsmanager.password.controller;

import com.vinibelo.passwordsmanager.password.model.PasswordGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("/passwords")
public class PasswordGeneratorController {

    @PostMapping()
    public Map<String, String> generatePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        return Map.of("newPassword", passwordGenerator.generatePassword());
    }
}
