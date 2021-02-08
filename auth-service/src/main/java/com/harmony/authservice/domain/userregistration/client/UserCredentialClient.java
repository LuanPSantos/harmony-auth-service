package com.harmony.authservice.domain.userregistration.client;

import com.harmony.authservice.domain.userregistration.client.response.UserCredentialResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "UserRegistrationClient",
        url = "${user-registration.url}",
        configuration = {
                UserRegistrationErrorDecoder.class
        }
)
@RequestMapping("users/credentials")
public interface UserCredentialClient {

    @GetMapping
    UserCredentialResponse findUserCredentialByEmail(@RequestParam("email") String email);
}
