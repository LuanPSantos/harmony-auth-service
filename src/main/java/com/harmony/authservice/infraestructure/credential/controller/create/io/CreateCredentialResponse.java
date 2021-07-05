package com.harmony.authservice.infraestructure.credential.controller.create.io;

public class CreateCredentialResponse {

    private Long id;

    public CreateCredentialResponse() {
    }

    public CreateCredentialResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
