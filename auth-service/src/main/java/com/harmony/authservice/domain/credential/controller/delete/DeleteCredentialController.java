package com.harmony.authservice.domain.credential.controller.delete;

import com.harmony.authservice.domain.credential.service.delete.DeleteCredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credentials")
public class DeleteCredentialController {

    private final DeleteCredentialService deleteCredentialService;

    public DeleteCredentialController(DeleteCredentialService deleteCredentialService) {
        this.deleteCredentialService = deleteCredentialService;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        deleteCredentialService.deleteById(id);
    }
}
