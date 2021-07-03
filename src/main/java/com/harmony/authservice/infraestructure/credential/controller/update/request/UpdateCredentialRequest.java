package com.harmony.authservice.infraestructure.credential.controller.update.request;

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
