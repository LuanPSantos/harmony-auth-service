package com.harmony.authservice.infraestructure.credential.controller.update.io;

import javax.validation.constraints.NotBlank;

public class UpdateCredentialRequest {

    private String email;
    private String rawPassword;
    @NotBlank(message = "Senha atual não informada")
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
