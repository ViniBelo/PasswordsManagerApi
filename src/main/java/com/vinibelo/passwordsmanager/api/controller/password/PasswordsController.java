package com.vinibelo.passwordsmanager.api.controller.password;

import com.vinibelo.passwordsmanager.api.controller.password.dto.password.CreatePasswordRequestDto;
import com.vinibelo.passwordsmanager.api.controller.password.dto.password.CreatePasswordResponseDto;
import com.vinibelo.passwordsmanager.api.controller.password.exception.UnautorizedException;
import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.api.service.password.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
            Authentication authentication
    ) {
        try {
            var username = authentication.getName();
            Password password = passwordService.newPassword(username, createPasswordRequestDto.platformId());
            String uri = "/passwords/" + password.getId();
            CreatePasswordResponseDto responseDto = new CreatePasswordResponseDto(
                    password.getPlatform().getNick(),
                    password.getPassword());
            return ResponseEntity.created(URI.create(uri)).body(responseDto);
        } catch (ChangeSetPersister.NotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (UnautorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
