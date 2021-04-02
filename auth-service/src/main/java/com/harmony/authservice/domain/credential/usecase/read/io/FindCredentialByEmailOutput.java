package com.harmony.authservice.domain.credential.usecase.read.io;

import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.model.Password;
import com.harmony.authservice.domain.credential.model.Role;

public class FindCredentialByEmailOutput {
    private final Long id;
    private final Email email;
    private final Password password;
    private final Role role;

    public FindCredentialByEmailOutput(Long id, Email email, Password password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
