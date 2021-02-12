package com.harmony.authservice.domain.credential.controller.read.response;

public class FindCredentialByEmailResponse {

    private String email;
    private String password;

    public FindCredentialByEmailResponse() {
    }

    public FindCredentialByEmailResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
