package com.harmony.authservice.domain.credential.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Objects;

@Embeddable
public class Password {

    @Transient
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Column(name = "password", nullable = false)
    private String value;

    public Password() {
    }

    public Password(String password) {
        this.value = password;
    }

    public String getValue() {
        return value;
    }

    public boolean matches(String rawPassword) {
        return passwordEncoder.matches(rawPassword, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return matches(password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
