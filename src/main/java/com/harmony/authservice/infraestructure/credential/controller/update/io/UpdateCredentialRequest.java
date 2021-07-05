package com.harmony.authservice.infraestructure.credential.controller.update.io;

public class UpdateCredentialRequest {

    private String email;
    private String rawPassword;

    public String getEmail() {
        return email;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
