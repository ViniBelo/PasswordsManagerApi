package com.vinibelo.passwordsmanager.password.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringShuffler {
    public String shuffle(String value) {
        List<Character> characters = new java.util.ArrayList<>(
                value.chars()
                        .mapToObj(c -> (char) c)
                        .toList()
        );
        Collections.shuffle(characters);
        return characters.stream().map(Object::toString).collect(Collectors.joining());
    }
}
