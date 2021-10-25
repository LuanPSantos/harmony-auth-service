package com.harmony.authservice.app.usecase;

public interface UseCase<I, O> {

    O execute(I input) throws Exception;
}
