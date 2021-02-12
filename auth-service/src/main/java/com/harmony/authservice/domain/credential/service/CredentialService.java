package com.harmony.authservice.domain.credential.service;

import com.harmony.authservice.domain.credential.model.Credential;
import com.harmony.authservice.domain.credential.model.Email;
import com.harmony.authservice.domain.credential.repository.CredentialRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredentialService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public Credential findById(Long id) throws Exception {
        return credentialRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Credenciais não encontradas."));
    }

    public Credential findByEmail(String email) throws UsernameNotFoundException {
        return credentialRepository
                .findByEmail(new Email(email))
                .orElseThrow(() -> new UsernameNotFoundException("Credentiais não encontradas por email"));
    }

    public Credential save(Credential credential) {
        return credentialRepository.save(credential);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }
}
