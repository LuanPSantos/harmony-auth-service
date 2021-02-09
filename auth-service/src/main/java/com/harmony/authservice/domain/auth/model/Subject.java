package com.harmony.authservice.domain.auth.model;

public class Subject {

    private String username;
    private String role;

    public Subject() {
    }

    public Subject(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
