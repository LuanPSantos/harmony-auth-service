package com.harmony.authservice.infraestructure.credential.controller.update.io;

public class UpdateCredentialResponse {
    private final String email;

    public UpdateCredentialResponse(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
