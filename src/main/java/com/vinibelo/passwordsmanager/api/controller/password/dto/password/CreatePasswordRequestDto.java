package com.vinibelo.passwordsmanager.api.controller.password.dto.password;

import java.util.UUID;

public record CreatePasswordRequestDto(
        UUID platformId
) {
    public CreatePasswordRequestDto {
        if (platformId == null) {
            throw new IllegalArgumentException("Platform id cannot be null");
        }
    }
}
