package com.harmony.authservice.domain.credential.usecase.read.io;

public class FindCredentialByEmailInput {

    private final String email;

    public FindCredentialByEmailInput(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
