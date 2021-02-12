package com.harmony.authservice.domain.credential.controller.update;

import com.harmony.authservice.domain.credential.controller.update.request.UpdateCredentialRequest;
import com.harmony.authservice.domain.credential.service.update.UpdateCredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auths/credentials")
public class UpdateCredentialController {

    private final UpdateCredentialService updateCredentialService;

    public UpdateCredentialController(UpdateCredentialService updateCredentialService) {
        this.updateCredentialService = updateCredentialService;
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody UpdateCredentialRequest request) throws Exception {
        updateCredentialService.update(id, request.getEmail(), request.getPassword());
    }
}
