package com.vinibelo.passwordsmanager.api.controller.password.dto.platform;

import java.util.UUID;

public record PlatformsToListDto(
        UUID id,
        String nick
) { }
