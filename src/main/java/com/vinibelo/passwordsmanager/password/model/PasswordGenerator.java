package com.vinibelo.passwordsmanager.password.model;

import com.vinibelo.passwordsmanager.password.utils.SymbolChar;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordGenerator {
    SecureRandom random = new SecureRandom();

    String generatePassword() {
        StringBuilder password = new StringBuilder();
        SymbolChar symbolChar = new SymbolChar(random);
        int firstSplit = random.nextInt(1, 17);
        int secondSplit = random.nextInt(1, (20 - firstSplit) - 2);
        int thirdSplit = random.nextInt(1, (20 - firstSplit - secondSplit) - 1);
        int fourthSplit = 20 - secondSplit - firstSplit - thirdSplit;
        IntStream.range(0, firstSplit).forEach(i -> {
            int randomNumber = random.nextInt(0, 9);
            password.append(randomNumber);
        });
        IntStream.range(0, secondSplit).forEach(i -> {
            char randomLowerCaseChar = (char) random.nextInt(97, 122);
            password.append(randomLowerCaseChar);
        });
        IntStream.range(0, thirdSplit).forEach(i -> {
            char randomUpperCaseChar = (char) random.nextInt(65, 90);
            password.append(randomUpperCaseChar);
        });
        IntStream.range(0, fourthSplit).forEach(i -> {
            char randomSymbol = symbolChar.randomSymbolChar();
            password.append(randomSymbol);
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
