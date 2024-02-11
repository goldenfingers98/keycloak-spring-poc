package net.groupetelnet.example.authenticationadapter.service;

import jakarta.ws.rs.core.Response;
import net.groupetelnet.example.authenticationadapter.domain.AuthTokenResponse;
import net.groupetelnet.example.authenticationadapter.domain.User;
import net.groupetelnet.example.authenticationadapter.domain.UserCredential;
import net.groupetelnet.example.authenticationadapter.domain.mapper.AuthTokenResponseMapper;
import net.groupetelnet.example.authenticationadapter.domain.mapper.UserMapper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsersResource usersResource;

    @Autowired
    private KeycloakBuilder applicationKeycloakBuilderPrototype;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthTokenResponseMapper authTokenResponseMapper;

    @Override
    public String addUser(User user) {
        try (final Response response = usersResource.create(userMapper.toUserRepresentation(user))) {
            if (response.getStatus() == 201) {
                UserRepresentation createdUser = usersResource
                        .searchByUsername(user.username(), true)
                        .stream()
                        .findFirst()
                        .orElseThrow(NoSuchElementException::new);
                return createdUser.getId();
            }
            throw new IllegalStateException(String.format("User couldn't be added: %s response.", response.getStatus()));
        }

    }

    @Override
    public AuthTokenResponse signIn(UserCredential userCredential) {
        AccessTokenResponse tokenResponse;
        try (Keycloak keycloak = applicationKeycloakBuilderPrototype.username(userCredential.username())
                .password(userCredential.password())
                .build()) {
            tokenResponse = keycloak.tokenManager().getAccessToken();
        }
        return authTokenResponseMapper.toAuthTokenResponse(tokenResponse);
    }
}
