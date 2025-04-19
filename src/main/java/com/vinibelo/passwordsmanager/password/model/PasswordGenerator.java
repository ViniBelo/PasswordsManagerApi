package com.vinibelo.passwordsmanager.password.model;

import java.util.Random;
import java.util.stream.IntStream;

public class PasswordGenerator {
    String generatePassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        IntStream.range(0, 20).forEach(i -> {
            password.append(random.nextInt(0, 9));
        });
        return password.toString();
    }
}
