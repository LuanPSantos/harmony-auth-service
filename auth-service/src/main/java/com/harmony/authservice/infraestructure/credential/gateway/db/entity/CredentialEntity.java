package com.harmony.authservice.infraestructure.credential.gateway.db.entity;

import javax.persistence.*;

@Entity
public class CredentialEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String email;
    private String password;
    private String role;

    public CredentialEntity() {
    }

    public CredentialEntity(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
