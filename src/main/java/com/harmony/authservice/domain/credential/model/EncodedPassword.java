package com.harmony.authservice.domain.credential.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodedPassword extends Password {

    public EncodedPassword(String rawPassword) {
        super(new BCryptPasswordEncoder().encode(rawPassword));
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public boolean matches(Password password) {
        if(password instanceof EncodedPassword) {
            throw new IllegalArgumentException();
        }
        return new BCryptPasswordEncoder().matches(password.get(), value);
    }
}