package com.vinibelo.passwordsmanager.api.controller.password.dto;

import java.util.UUID;

public record PasswordsToListDto(
        UUID id,
        String nick
) { }
