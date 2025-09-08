package com.vinibelo.passwordsmanager.api.controller.password;

import com.vinibelo.passwordsmanager.api.controller.password.dto.platform.CreatePlatformRequestDto;
import com.vinibelo.passwordsmanager.api.controller.password.dto.platform.ListPlatformsResponseDto;
import com.vinibelo.passwordsmanager.api.controller.password.dto.platform.ShowPlatformResponseDto;
import com.vinibelo.passwordsmanager.api.controller.password.exception.UnautorizedException;
import com.vinibelo.passwordsmanager.api.service.password.PlatformService;
import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.entity.Platform;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("platforms")
public class PlatformsController {
    final PlatformService platformService;

    PlatformsController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @PostMapping
    public ResponseEntity<Void> createPlatform(@RequestBody CreatePlatformRequestDto createPlatformRequestDto,
                                               HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        platformService.createPlatform(token,
                createPlatformRequestDto.nick(),
                createPlatformRequestDto.renewIn());
        var uri = "/platforms/" + createPlatformRequestDto.nick();
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @GetMapping
    public ResponseEntity<ListPlatformsResponseDto> listPlatforms(
            @RequestParam(required = false, defaultValue = "20") int limit,
            @RequestParam(required = false, defaultValue = "0") int page,
            Authentication authentication
    ) {
        var username = authentication.getName();
        Page<Platform> platforms = platformService.listPlatformsByUser(username, limit, page);
        ListPlatformsResponseDto listPlatformsResponseDto = ListPlatformsResponseDto.build(platforms, page);
        return ResponseEntity.ok().body(listPlatformsResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowPlatformResponseDto> showPlatform(@PathVariable UUID id,
                                                                Authentication authentication) {
        try {
            var username = authentication.getName();
            Platform platform = platformService.getPlatform(username, id);
            ShowPlatformResponseDto response = new ShowPlatformResponseDto(
                    platform.getNick(),
                    platform.getPasswords().stream().map(Password::getPassword).toList(),
                    platform.getRenewIn()
            );
            return ResponseEntity.ok().body(response);
        } catch (ChangeSetPersister.NotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (UnautorizedException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
