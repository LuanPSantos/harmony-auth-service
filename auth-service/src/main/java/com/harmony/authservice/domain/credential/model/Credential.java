package com.harmony.authservice.domain.credential.model;

import com.harmony.authservice.domain.credential.exception.PasswordInvalidException;

import java.util.Objects;

public class Credential {

    private CredentialId id;
    private Email email;
    private Password password;
    private Role role;

    public Credential(Email email, Password password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Credential(CredentialId id, Email email, Password password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Credential(CredentialId id, Email email, Password password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public CredentialId getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public void updateEmail(Email email) {
        if (email.get() != null) {
            this.email = email;
        }
    }

    public Password getPassword() {
        return password;
    }

    public void updatePassword(Password password) {
        if (password != null && password.get() != null) {
            this.password = password;
        }
    }

    public void validate() throws Exception {
        if (email.get().contains(password.get())) {
            throw new PasswordInvalidException("O email não pode conter a senha");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credential that = (Credential) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
