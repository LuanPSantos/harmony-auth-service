package com.harmony.authservice.app.usecase.credential.delete.io;

public class DeleteCredentialInput {
    private final Long id;

    public DeleteCredentialInput(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
