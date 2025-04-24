package com.vinibelo.passwordsmanager.password.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class PasswordGenaratorTest {
    @Test()
    void shouldGenerateAStringPassword() {
        // Given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // When
        String password = passwordGenerator.generatePassword();

        // Then
        assertThat(password, instanceOf(String.class));
    }

    @Test()
    void shouldGenerateAPasswordWithTwentyCharacters() {
        // Given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // When
        String password = passwordGenerator.generatePassword();

        // Then
        Assertions.assertEquals(20, password.length());
    }

    @Test()
    void shouldGenerateAPasswordWithAtLeastOneLowercaseCharacter() {
        // Given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // When
        String password = passwordGenerator.generatePassword();

        // Then
        Assertions.assertTrue(password.matches(".*[a-z].*"));
    }

    @Test()
    void shouldGenerateAPasswordWithAtLeastOneUppercaseCharacter() {
        // Given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // When
        String password = passwordGenerator.generatePassword();

        // Then
        Assertions.assertTrue(password.matches(".*[A-Z].*"));
    }

    @Test()
    void shouldGenerateAPasswordWithAtLeastOneSymbol() {
        // Given
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // When
        String password = passwordGenerator.generatePassword();

        // Then
        boolean containsSymbol = password.chars().anyMatch(c ->
                (c >= 33 && c <= 47) ||
                (c >= 58 && c <= 64) ||
                (c >= 91 && c <= 96) ||
                (c >= 123 && c <= 126)
        );

        Assertions.assertTrue(containsSymbol);
    }
}
