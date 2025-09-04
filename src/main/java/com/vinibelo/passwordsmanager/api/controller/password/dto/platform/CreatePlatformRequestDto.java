package com.vinibelo.passwordsmanager.api.controller.password.dto.platform;

public record CreatePlatformRequestDto(
        String nick,
        Integer renewIn
) {
    public CreatePlatformRequestDto {
        if (nick == null || nick.isBlank()) {
            throw new IllegalArgumentException("Nick cannot be null or blank");
        }
    }
}
