package com.harmony.authservice.domain.auth.service;

import com.harmony.authservice.domain.auth.model.JWTAuthorization;
import com.harmony.authservice.domain.auth.model.Subject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JWTAuthorization authenticate(String username, String password) throws Exception {
        Authentication authentication = authenticateByUsernameAndPassword(username, password);

        return mountJWTAuthorization(authentication);
    }

    public JWTAuthorization authorize(String username) throws Exception {
        Authentication authentication = authenticateByUsernameAndPassword(username, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            return mountJWTAuthorization(authentication);
        }

        throw new Exception("Não autorizado!");
    }

    private Authentication authenticateByUsernameAndPassword(String username, String password) {
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private JWTAuthorization mountJWTAuthorization(Authentication authentication) throws Exception {
//        String role = authentication
//                .getAuthorities()
//                .stream().map((GrantedAuthority::getAuthority))
//                .findFirst()
//                .orElseThrow(() -> new Exception("Erro ao buscar a role"));

        return new JWTAuthorization(new Subject(authentication.getName(), "role"));
    }
}
