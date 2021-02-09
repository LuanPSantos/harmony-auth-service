package com.harmony.authservice.domain.userregistration.model;

public class User {

    private String email;
    private Password password;
    private String role;

    public User(String email, Password password) {
        this.email = email;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
