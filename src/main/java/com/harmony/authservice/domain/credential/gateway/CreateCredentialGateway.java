package com.harmony.authservice.domain.credential.gateway;

import com.harmony.authservice.domain.credential.model.Credential;

public interface CreateCredentialGateway {

    Credential create(Credential credential);
}
