package com.harmony.authservice.domain.auth.authentication.usecase.io;

public class AuthenticationInput {
    private final String email;
    private final String rawPassword;

    public AuthenticationInput(String email, String password) {
        this.email = email;
        this.rawPassword = password;
    }

    public String getEmail() {
        return email;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
