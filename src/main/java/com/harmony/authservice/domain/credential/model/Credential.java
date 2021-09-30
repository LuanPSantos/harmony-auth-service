package com.harmony.authservice.domain.credential.model;

import com.harmony.authservice.domain.credential.exception.PasswordInvalidException;

import java.util.Objects;

public class Credential {

    private final CredentialId id;
    private Email email;
    private Password password;
    private final Role role;

    private Credential(CredentialId id, Email email, Password password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
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
        if (email != null && email.get() != null) {
            this.email = email;
        }
    }

    public Password getPassword() {
        return password;
    }

    public void updatePassword(Password password) {
        if (password != null && password.get() != null) {
            if (password instanceof EncodedPassword) {
                this.password = password;
            } else {
                this.password = EncodedPassword.fromRawPassword(password.get());
            }
        }
    }

    // TODO tá feio esse validate aqui - vou ver se tiro pra outra classe
    public void validate() throws PasswordInvalidException {
        if (email.get().contains(password.get())) {
            throw new PasswordInvalidException();
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

    public static class Builder {

        private CredentialId id;
        private Email email;
        private Password password;
        private Role role;

        public Builder withEmail(String email) {
            this.email = new Email(email);
            return this;
        }

        public Builder withEmail(Email email) {
            this.email = email;
            return this;
        }

        public Builder withRawPassword(String password) {
            this.password = new Password(password);
            return this;
        }

        public Builder withRawPassword(Password password) {
            this.password = password;
            return this;
        }

        public Builder withEncodedPassword(String password) {
            this.password = new EncodedPassword(password);
            return this;
        }

        public Builder withEncodedPassword(EncodedPassword password) {
            this.password = password;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withRole(String role) {
            this.role = Role.valueOf(role);
            return this;
        }

        public Builder withId(Long id) {
            this.id = new CredentialId(id);
            return this;
        }

        public Builder withId(CredentialId id) {
            this.id = id;
            return this;
        }

        public Credential build() {
            return new Credential(id, email, password, role);
        }
    }
}
