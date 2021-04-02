package com.harmony.authservice.domain.usecase;

public interface UseCase<I, O> {

    O execute(I input) throws Exception;
}
