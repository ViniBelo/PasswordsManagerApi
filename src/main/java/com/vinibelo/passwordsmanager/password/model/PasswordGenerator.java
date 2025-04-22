package com.vinibelo.passwordsmanager.password.model;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordGenerator {
    Random random = new Random();

    String generatePassword() {
        StringBuilder password = new StringBuilder();
        int firstDivision = random.nextInt(1, 18);
        int secondDivision = random.nextInt(1, (20 - firstDivision) - 1);
        int thirdDivision = 20 - secondDivision - firstDivision;
        IntStream.range(0, firstDivision).forEach(i -> {
            int randomNumber = random.nextInt(0, 9);
            password.append(randomNumber);
        });
        IntStream.range(0, secondDivision).forEach(i -> {
            char randomLowerCaseChar = (char) random.nextInt(97, 122);
            password.append(randomLowerCaseChar);
        });
        IntStream.range(0, thirdDivision).forEach(i -> {
            char randomUpperCaseChar = (char) random.nextInt(65, 90);
            password.append(randomUpperCaseChar);
        });
        return shuffle(password.toString());
    }

    private String shuffle(String password) {
        List<Character> characters = new java.util.ArrayList<>(
                password.chars()
                        .mapToObj(c -> (char) c)
                        .toList()
        );
        Collections.shuffle(characters);
        return characters.stream().map(Object::toString).collect(Collectors.joining());
    }
}
