package com.vinibelo.passwordsmanager.api.controller.password;

import com.vinibelo.passwordsmanager.api.controller.password.dto.CreatePasswordRequestDto;
import com.vinibelo.passwordsmanager.api.controller.password.dto.CreatePasswordResponseDto;
import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.domain.PasswordGenerator;
import com.vinibelo.passwordsmanager.api.service.password.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController()
@RequestMapping("passwords")
public class PasswordGeneratorController {
    PasswordService passwordService;

    public PasswordGeneratorController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping()
    public ResponseEntity<CreatePasswordResponseDto> generatePassword(
            @RequestBody CreatePasswordRequestDto createPasswordRequestDto,
            HttpServletRequest request
    ) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String token = request.getHeader("Authorization");
        Password password = passwordService.save(
                createPasswordRequestDto.nick(),
                passwordGenerator.generatePassword(),
                token);
        String uri = "/passwords/" + password.getId();
        CreatePasswordResponseDto responseDto = new CreatePasswordResponseDto(
                createPasswordRequestDto.nick(),
                password.getPassword());
        return ResponseEntity.created(URI.create(uri)).body(responseDto);
    }
}
