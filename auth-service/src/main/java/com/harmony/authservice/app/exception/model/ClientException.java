package com.harmony.authservice.app.exception.model;

import java.util.ArrayList;
import java.util.List;

public class ClientException extends Exception {

    private final List<Error> errors = new ArrayList<>();

    public ClientException(List<Error> errors) {
        this.errors.addAll(errors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
