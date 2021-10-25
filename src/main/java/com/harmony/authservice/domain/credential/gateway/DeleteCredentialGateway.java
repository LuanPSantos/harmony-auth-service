package com.harmony.authservice.domain.credential.gateway;

import com.harmony.authservice.domain.credential.model.CredentialId;

public interface DeleteCredentialGateway {

    void deleteById(CredentialId id);
}
