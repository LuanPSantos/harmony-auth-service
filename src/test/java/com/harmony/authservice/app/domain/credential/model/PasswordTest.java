package com.harmony.authservice.app.domain.credential.model;

import com.harmony.authservice.domain.credential.model.EncodedPassword;
import com.harmony.authservice.domain.credential.model.Password;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {

    @Test
    void ShouldMatchRawPasswords() {
        Password p1 = new Password("abc");
        Password p2 = new Password("abc");

        assertTrue(p1.matches(p2));
    }

    @Test
    void ShouldNotMatchRawPasswords() {
        Password p1 = new Password("abc");
        Password p2 = new Password("adc");

        assertFalse(p1.matches(p2));
    }

    @Test
    void ShouldMatchRawPasswordAndEncodedPassword() {
        Password p1 = new EncodedPassword("abc");
        Password p2 = new Password("abc");

        assertTrue(p1.matches(p2));
    }

    @Test
    void ShouldNotMatchRawPasswordAndEncodedPassword() {
        Password p1 = new EncodedPassword("abc");
        Password p2 = new Password("adc");

        assertFalse(p1.matches(p2));
    }

    @Test
    void ShouldNotMatchEncodedPasswords() {
        Password p1 = new EncodedPassword("abc");
        Password p2 = new EncodedPassword("adc");

        assertThrows(IllegalArgumentException.class, () -> p1.matches(p2));
    }
}
