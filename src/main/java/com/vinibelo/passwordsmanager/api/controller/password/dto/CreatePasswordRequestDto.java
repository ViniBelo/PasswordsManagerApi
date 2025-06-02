package com.vinibelo.passwordsmanager.api.controller.password.dto;

public record CreatePasswordRequestDto(
        String nick,
        Integer renew_in
) { }
