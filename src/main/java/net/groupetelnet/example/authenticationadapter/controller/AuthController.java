package net.groupetelnet.example.authenticationadapter.controller;

import net.groupetelnet.example.authenticationadapter.domain.AuthTokenResponse;
import net.groupetelnet.example.authenticationadapter.domain.User;
import net.groupetelnet.example.authenticationadapter.domain.UserCredential;
import net.groupetelnet.example.authenticationadapter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerApi {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<String> registerUser(User user) {
        return ResponseEntity.ok(authService.addUser(user));
    }

    @Override
    public ResponseEntity<AuthTokenResponse> signIn(UserCredential userCredential) {
        return ResponseEntity.ok(authService.signIn(userCredential));
    }
}
