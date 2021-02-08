package com.harmony.authservice.domain.userregistration.repository;

import com.harmony.authservice.domain.userregistration.client.UserCredentialClient;
import com.harmony.authservice.domain.userregistration.client.response.UserCredentialResponse;
import com.harmony.authservice.domain.userregistration.model.Password;
import com.harmony.authservice.domain.userregistration.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final UserCredentialClient userCredentialClient;

    public UserRepository(UserCredentialClient userCredentialClient) {
        this.userCredentialClient = userCredentialClient;
    }

    public User findByEmail(String email) {
        UserCredentialResponse response = userCredentialClient.findUserCredentialByEmail(email);

        return new User(response.getEmail(), new Password(response.getPassword()));
    }
}
