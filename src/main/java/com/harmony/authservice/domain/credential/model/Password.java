package com.harmony.authservice.domain.credential.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Objects;

public class Password {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final String value;

    public Password(String password) {
        this.value = password;
    }

    public String get() {
        return value;
    }

    public boolean matches(String rawPassword) {
        return passwordEncoder.matches(rawPassword, value);
    }
}
