package com.harmony.authservice.domain.token.model;

public class Token {

    private final String value;

    public Token(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
