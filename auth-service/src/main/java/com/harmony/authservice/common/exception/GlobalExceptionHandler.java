package com.harmony.authservice.common.exception;

import com.harmony.authservice.common.exception.model.ClientException;
import com.harmony.authservice.common.exception.model.Error;
import com.harmony.authservice.common.exception.response.ExceptionResponse;
import com.harmony.authservice.domain.auth.exception.AuthenticationException;
import com.harmony.authservice.domain.credential.exception.CredentialNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ClientException.class})
    public ResponseEntity<ExceptionResponse> handle(ClientException exception) {

        ExceptionResponse body = new ExceptionResponse(exception.getErrors());

        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    @ExceptionHandler({
            ExpiredJwtException.class,
            AuthenticationException.class
    })
    public ResponseEntity<ExceptionResponse> handle(Exception exception) {
        ExceptionResponse body = new ExceptionResponse(Collections.singletonList(new Error(exception.getMessage())));

        return ResponseEntity.status(UNAUTHORIZED).body(body);
    }

    @ExceptionHandler({CredentialNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handle(CredentialNotFoundException exception) {
        ExceptionResponse body = new ExceptionResponse(Collections.singletonList(new Error(exception.getMessage())));

        return ResponseEntity.status(BAD_REQUEST).body(body);
    }
}
