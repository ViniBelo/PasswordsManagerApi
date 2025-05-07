package com.vinibelo.passwordsmanager.api.controller.password;

import com.vinibelo.passwordsmanager.api.controller.password.dto.*;
import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.domain.PasswordGenerator;
import com.vinibelo.passwordsmanager.api.service.password.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

    @GetMapping()
    public ResponseEntity<ListPasswordsResponseDto> listPasswords(
            @RequestParam(required = false, defaultValue = "20") int limit,
            @RequestParam(required = false, defaultValue = "0") int page,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization");
        Page<Password> passwords = passwordService.searchPasswordByUser(token, limit, page);
        ListPasswordsResponseDto listPasswordsResponseDto = buildListPasswordsResponseDto(passwords, page);
        return ResponseEntity.ok().body(listPasswordsResponseDto);
    }

    private ListPasswordsResponseDto buildListPasswordsResponseDto(Page<Password> passwords, int page) {
        return new ListPasswordsResponseDto(
                passwords.stream()
                        .map(password -> new PasswordsToListDto(password.getId(), password.getNick()))
                        .toList(),
                passwords.getTotalPages(),
                page,
                passwords.getTotalElements()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowPasswordResponseDto> searchPasswordById(@PathVariable UUID id) {
        Password password = passwordService.searchPasswordById(id);
        ShowPasswordResponseDto response = buildShowPasswordResponseDto(password);
        return ResponseEntity.ok().body(response);
    }

    private ShowPasswordResponseDto buildShowPasswordResponseDto(Password password) {
        return new ShowPasswordResponseDto(
                password.getId(),
                password.getNick(),
                password.getPassword()
        );
    }
}
