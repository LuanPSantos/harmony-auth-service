package com.harmony.authservice.domain.credential.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodedPassword extends Password {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public EncodedPassword(String password) {
        super(password);
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public boolean matches(Password password) {
        return passwordEncoder.matches(password.get(), value);
    }
}
