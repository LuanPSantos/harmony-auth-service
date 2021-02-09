package com.harmony.authservice.domain.userregistration.service;

import com.harmony.authservice.domain.userregistration.model.User;
import com.harmony.authservice.domain.userregistration.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
