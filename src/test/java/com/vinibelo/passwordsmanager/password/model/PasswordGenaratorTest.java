package com.vinibelo.passwordsmanager.password.model;

import org.junit.jupiter.api.Test;
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
}
