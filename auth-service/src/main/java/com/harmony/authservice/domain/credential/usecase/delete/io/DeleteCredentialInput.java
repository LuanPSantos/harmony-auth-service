package com.harmony.authservice.domain.credential.usecase.delete.io;

public class DeleteCredentialInput {
    private final Long id;

    public DeleteCredentialInput(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
