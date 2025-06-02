package com.vinibelo.passwordsmanager.api.controller.password.dto;

import com.vinibelo.passwordsmanager.password.entity.Password;

import java.util.UUID;

public record ShowPasswordResponseDto(
        UUID id,
        String nick,
        String password
) {
    public static ShowPasswordResponseDto build(Password password) {
        return new ShowPasswordResponseDto(
                password.getId(),
                password.getPlatform().getNick(),
                password.getPassword()
        );
    }
}
