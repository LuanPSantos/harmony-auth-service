package com.harmony.authservice.domain.credential.usecase.update.io;

public class UpdateCredentialInput {
    private Long id;
    private String email;
    private String password;

    public UpdateCredentialInput(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
