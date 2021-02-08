package com.harmony.authservice.common.exception;

import com.harmony.authservice.common.exception.model.ClientException;
import com.harmony.authservice.common.exception.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ClientException.class})
    public ResponseEntity<ExceptionResponse> handle(ClientException exception) {

        ExceptionResponse dto = new ExceptionResponse(exception.getErrors());

        return ResponseEntity.status(BAD_REQUEST).body(dto);
    }
}
