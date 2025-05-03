package com.vinibelo.passwordsmanager.api.controller.password.dto;

import java.util.List;

public record ListPasswordsResponseDto(
        List<PasswordsToListDto> passwords,
        int totalPages,
        int currentPage,
        Long totalItems
) { }
