package com.harmony.authservice.domain.userregistration.model;

public class Password {

    private String value;

    public Password(String value) {
        this.value = value;
    }

    public void checkMatches(String password) throws Exception {
        if(password.matches(password) == false) {
            throw new Exception("Senha inválida");
        }
    }
}
