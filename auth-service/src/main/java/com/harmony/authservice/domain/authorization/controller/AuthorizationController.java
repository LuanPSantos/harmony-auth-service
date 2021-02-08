package com.harmony.authservice.domain.authorization.controller;

import com.harmony.authservice.domain.authorization.service.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authorizations")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public ResponseEntity<Void> authorize(@RequestHeader("Authorization") String authorization) throws Exception {
        String newAuthorizationToken = authorizationService.authorize(authorization);

        return ResponseEntity.ok().header("Authorization", newAuthorizationToken).build();
    }
}
