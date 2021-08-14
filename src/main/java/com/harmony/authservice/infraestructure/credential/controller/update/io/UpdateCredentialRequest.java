package com.harmony.authservice.infraestructure.credential.controller.update.io;

public class UpdateCredentialRequest {

    private String email;
    private String rawPassword;
    private String oldRawPassword;

    public String getOldRawPassword() {
        return oldRawPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}
