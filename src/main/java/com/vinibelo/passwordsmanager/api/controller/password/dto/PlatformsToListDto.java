package com.vinibelo.passwordsmanager.api.controller.password.dto;

import java.util.UUID;

public record PlatformsToListDto(
        UUID id,
        String nick
) { }
