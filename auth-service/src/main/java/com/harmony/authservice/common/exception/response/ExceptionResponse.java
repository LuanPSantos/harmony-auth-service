package com.harmony.authservice.common.exception.response;

import com.harmony.authservice.common.exception.model.Error;

import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse {

    private final List<Error> errors = new ArrayList<>();

    public ExceptionResponse() {
    }

    public ExceptionResponse(List<Error> errors) {
        this.errors.addAll(errors);
    }

    public List<Error> getErrors() {
        return errors;
    }

}
