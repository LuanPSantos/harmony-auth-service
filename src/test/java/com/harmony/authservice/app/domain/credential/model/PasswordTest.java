package com.harmony.authservice.app.domain.credential.model;

import com.harmony.authservice.domain.credential.model.EncodedPassword;
import com.harmony.authservice.domain.credential.model.RawPassword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {

    @Test
    void ShouldMatchRawPasswords() {
        RawPassword p1 = new RawPassword("abc");
        RawPassword p2 = new RawPassword("abc");

        assertEquals(p1, p2);
    }

    @Test
    void ShouldNotMatchRawPasswords() {
        RawPassword p1 = new RawPassword("abc");
        RawPassword p2 = new RawPassword("adc");

        assertNotEquals(p1, p2);
    }

    @Test
    void ShouldMatchRawPasswordAndEncodedPassword() {
        RawPassword p2 = new RawPassword("abc");
        EncodedPassword p1 = EncodedPassword.encodeRawPassword(p2);

        assertTrue(p1.matches(p2));
    }

    @Test
    void ShouldNotMatchRawPasswordAndEncodedPassword() {
        EncodedPassword p1 = new EncodedPassword("abc");
        RawPassword p2 = new RawPassword("adc");

        assertFalse(p1.matches(p2));
    }
}
