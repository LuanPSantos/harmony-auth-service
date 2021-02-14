package com.harmony.authservice.domain.credential.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Credential {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Embedded
    private Email email;
    @Embedded
    private Password password;

    public Credential() {
    }

    public Credential(String email, String password) {
        this.email = new Email(email);
        this.password = new Password(password);
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
            throw new Exception("A senha não pode conter a senha");
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
