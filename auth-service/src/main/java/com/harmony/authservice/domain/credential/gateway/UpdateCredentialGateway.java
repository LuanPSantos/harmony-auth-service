package com.harmony.authservice.domain.credential.gateway;

import com.harmony.authservice.domain.credential.model.Credential;

public interface UpdateCredentialGateway {

    void update(Credential credential);
}
