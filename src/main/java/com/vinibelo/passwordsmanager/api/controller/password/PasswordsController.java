package com.vinibelo.passwordsmanager.api.controller.password;

import com.vinibelo.passwordsmanager.api.controller.password.dto.password.CreatePasswordRequestDto;
import com.vinibelo.passwordsmanager.api.controller.password.dto.password.CreatePasswordResponseDto;
import com.vinibelo.passwordsmanager.api.controller.password.dto.password.ShowPasswordResponseDto;
import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.api.service.password.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController()
@RequestMapping("passwords")
public class PasswordsController {
    final PasswordService passwordService;

    public PasswordsController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping()
    public ResponseEntity<CreatePasswordResponseDto> generatePassword(
            @RequestBody CreatePasswordRequestDto createPasswordRequestDto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization");
        Password password = passwordService.newPassword(token, createPasswordRequestDto.platformId());
        String uri = "/passwords/" + password.getId();
        CreatePasswordResponseDto responseDto = new CreatePasswordResponseDto(
                password.getPlatform().getNick(),
                password.getPassword());
        return ResponseEntity.created(URI.create(uri)).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowPasswordResponseDto> searchPasswordById(@PathVariable UUID id) {
        Password password = passwordService.searchPasswordById(id);
        ShowPasswordResponseDto response = ShowPasswordResponseDto.build(password);
        return ResponseEntity.ok().body(response);
    }
}
