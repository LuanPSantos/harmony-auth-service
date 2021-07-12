package com.harmony.authservice.app.utils;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;

import static com.harmony.authservice.app.utils.CredentialTestConstants.EMAIL;
import static com.harmony.authservice.domain.credential.model.Role.USER;

public interface AuthorizationTestConstants {

    Long TTL = 2000L;
    JWTAuthorization AUTHORIZATION_TOKEN = JWTAuthorization.withEmailAndExpirationTimeAndRole(EMAIL.get(), TTL, USER);
    JWTAuthorization REFRESH_AUTHORIZATION_TOKEN = JWTAuthorization.withEmailAndExpirationTimeAndRole(EMAIL.get(), TTL, USER);;
}
