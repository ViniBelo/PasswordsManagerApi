package com.vinibelo.passwordsmanager.api.controller.password.dto;

import com.vinibelo.passwordsmanager.password.entity.Password;
import org.springframework.data.domain.Page;

import java.util.List;

public record ListPasswordsResponseDto(
        List<PasswordsToListDto> passwords,
        int totalPages,
        int currentPage,
        Long totalItems
) {
    public static ListPasswordsResponseDto build(Page<Password> passwords, int page) {
        return new ListPasswordsResponseDto(
                passwords.stream()
                        .map(password -> new PasswordsToListDto(password.getId(), password.getNick()))
                        .toList(),
                passwords.getTotalPages(),
                page,
                passwords.getTotalElements()
        );
    }
}
