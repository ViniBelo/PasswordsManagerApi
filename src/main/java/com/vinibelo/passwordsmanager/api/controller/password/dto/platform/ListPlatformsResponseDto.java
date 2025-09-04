package com.vinibelo.passwordsmanager.api.controller.password.dto.platform;

import com.vinibelo.passwordsmanager.password.entity.Platform;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListPlatformsResponseDto(
        List<PlatformsToListDto> platforms,
        int totalPages,
        int currentPage,
        Long totalItems
) {
    public static ListPlatformsResponseDto build(Page<Platform> platforms, int page) {
        return new ListPlatformsResponseDto(
                platforms.stream()
                        .map(platform -> new PlatformsToListDto(platform.getId(), platform.getNick()))
                        .toList(),
                platforms.getTotalPages(),
                page,
                platforms.getTotalElements()
        );
    }
}
