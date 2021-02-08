package com.harmony.authservice.domain.userregistration.model;

public class User {
    
    private String email;
    private Password password;

    public User(String email, Password password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
