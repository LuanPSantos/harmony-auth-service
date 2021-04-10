package com.harmony.authservice.domain.credential.model;

import java.util.Objects;

public class Credential {

    private Long id;
    private Email email;
    private Password password;
    private Role role;

    private Credential() {
    }

    public Credential(String email, String password, Role role) {
        this.email = new Email(email);
        this.password = new Password(password);
        this.role = role;
    }

    public Credential(Long id, String email, String password, Role role) {
        this.id = id;
        this.email = new Email(email);
        this.password = new Password(password);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Credential setEmail(Email email) {
        this.email = email;
        return this;
    }

    public Password getPassword() {
        return password;
    }

    public Credential setPassword(Password password) {
        this.password = password;
        return this;
    }

    public void validate() throws Exception {
        if (email.getValue().contains(password.getValue())) {
            throw new Exception("O email não pode conter a senha");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credential that = (Credential) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
