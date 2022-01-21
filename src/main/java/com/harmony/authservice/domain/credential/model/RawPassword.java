package com.harmony.authservice.domain.credential.model;

import java.util.Objects;

public class RawPassword {
    private final String value;

    public RawPassword(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawPassword that = (RawPassword) o;
        return Objects.equals(value, that.value);
    }

}
