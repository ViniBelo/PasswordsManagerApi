package com.vinibelo.passwordsmanager.api.controller.password;

import com.vinibelo.passwordsmanager.api.controller.password.dto.platform.CreatePlatformRequestDto;
import com.vinibelo.passwordsmanager.api.controller.password.dto.platform.ListPlatformsResponseDto;
import com.vinibelo.passwordsmanager.api.service.password.PlatformService;
import com.vinibelo.passwordsmanager.password.entity.Platform;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization");
        Page<Platform> platforms = platformService.listPlatformsByUser(token, limit, page);
        ListPlatformsResponseDto listPlatformsResponseDto = ListPlatformsResponseDto.build(platforms, page);
        return ResponseEntity.ok().body(listPlatformsResponseDto);
    }
}
