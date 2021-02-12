package com.harmony.authservice.domain.auth.service;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.auth.model.Subject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JWTAuthorization authenticate(String username, String password) throws Exception {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        String role = authentication
                .getAuthorities()
                .stream().map((GrantedAuthority::getAuthority))
                .findFirst()
                .orElseThrow(() -> new Exception("Erro ao buscar a role"));

        return new JWTAuthorization(new Subject(authentication.getName(), role));
    }
}
