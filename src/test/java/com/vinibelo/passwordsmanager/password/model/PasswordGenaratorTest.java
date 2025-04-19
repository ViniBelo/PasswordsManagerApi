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
}
