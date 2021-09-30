package com.harmony.authservice.domain.credential.model;

import java.util.Objects;

public class CredentialId {

    private final Long value;

    public CredentialId(Long value) {
        this.value = value;
    }

    public Long toLong() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialId that = (CredentialId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
