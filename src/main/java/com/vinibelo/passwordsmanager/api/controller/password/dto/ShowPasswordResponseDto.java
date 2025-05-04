package com.vinibelo.passwordsmanager.api.controller.password.dto;

import java.util.Date;
import java.util.UUID;

public record ShowPasswordResponseDto(
        UUID id,
        String nick,
        String password
) { }
