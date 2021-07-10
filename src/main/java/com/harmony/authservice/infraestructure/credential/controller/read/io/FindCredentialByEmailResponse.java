package com.harmony.authservice.infraestructure.credential.controller.read.io;

public class FindCredentialByEmailResponse {

    private Long id;
    private String email;

    public FindCredentialByEmailResponse() {
    }

    public FindCredentialByEmailResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}
