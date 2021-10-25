package com.harmony.authservice.infraestructure.passwordrecovery.createtoken.gateway.io;

public class TokenCreatedPayload {

    private final String token;
    private final String email;

    public TokenCreatedPayload(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }
}
