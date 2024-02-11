package net.groupetelnet.example.authenticationadapter.controller;

import net.groupetelnet.example.authenticationadapter.domain.AuthTokenResponse;
import net.groupetelnet.example.authenticationadapter.domain.User;
import net.groupetelnet.example.authenticationadapter.domain.UserCredential;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerApi {

    @PostMapping("/register")
    ResponseEntity<String> registerUser(@RequestBody User user);

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<AuthTokenResponse> signIn(UserCredential userCredential);
}
