package com.vinibelo.passwordsmanager.api.controller.password;

import com.vinibelo.passwordsmanager.api.controller.password.dto.ListPlatformsResponseDto;
import com.vinibelo.passwordsmanager.api.service.password.PlatformService;
import com.vinibelo.passwordsmanager.password.entity.Password;
import com.vinibelo.passwordsmanager.password.entity.Platform;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("platforms")
public class PlatformsController {
    final PlatformService platformService;

    PlatformsController(PlatformService platformService) {
        this.platformService = platformService;
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
