package com.harmony.authservice.domain.credential.gateway;

import com.harmony.authservice.domain.credential.model.Credential;

public interface SaveCredentialGateway {

    Credential save(Credential credential);
}
