package com.vinibelo.passwordsmanager.api.controller.password.dto.platform;

import java.util.List;

public record ShowPlatformResponseDto(
        String nick,
        List<String> password,
        Integer renewIn
) { }
