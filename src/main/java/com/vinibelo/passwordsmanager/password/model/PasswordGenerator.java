package com.vinibelo.passwordsmanager.password.model;

import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordGenerator {
    Random random = new Random();

    String generatePassword() {
        StringBuilder password = new StringBuilder();
        int firstDivision = random.nextInt(1, 19);
        IntStream.range(0, 20).forEach(i -> {
            if (i < firstDivision) {
                int randomNumber = random.nextInt(0, 9);
                password.append(randomNumber);
            } else {
                char randomLowerCaseChar = (char) random.nextInt(97, 122);
                password.append(randomLowerCaseChar);
            }
        });
        Collections.shuffle(password
                .toString()
                .chars()
                .boxed()
                .collect(Collectors.toList()));
        return password.toString();
    }
}
