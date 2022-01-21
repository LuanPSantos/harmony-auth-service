package com.harmony.authservice.domain.credential.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodedPassword {

    private final String value;

    public EncodedPassword(String value) {
        this.value = value;
    }

    public static EncodedPassword encodeRawPassword(RawPassword rawPassword) {
        return new EncodedPassword(new BCryptPasswordEncoder().encode(rawPassword.get()));
    }

    public String get() {
        return value;
    }

    public boolean matches(RawPassword password) {
        return new BCryptPasswordEncoder().matches(password.get(), value);
    }
}
