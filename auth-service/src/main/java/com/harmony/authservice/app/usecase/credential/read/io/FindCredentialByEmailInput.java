package com.harmony.authservice.app.usecase.credential.read.io;

public class FindCredentialByEmailInput {

    private final String email;

    public FindCredentialByEmailInput(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
