package net.groupetelnet.example.authenticationadapter.service;

import net.groupetelnet.example.authenticationadapter.domain.AuthTokenResponse;
import net.groupetelnet.example.authenticationadapter.domain.User;
import net.groupetelnet.example.authenticationadapter.domain.UserCredential;

public interface AuthService {
    String addUser(User user);
    AuthTokenResponse signIn(UserCredential userCredential);
}
