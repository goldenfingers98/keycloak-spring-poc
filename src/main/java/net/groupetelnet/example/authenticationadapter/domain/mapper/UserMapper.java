package net.groupetelnet.example.authenticationadapter.domain.mapper;

import net.groupetelnet.example.authenticationadapter.domain.User;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public class UserMapper {

    public UserRepresentation toUserRepresentation(User user) {
        if (user == null) {
            return null;
        }
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.username());
        userRepresentation.setFirstName(user.firstname());
        userRepresentation.setLastName(user.lastname());
        userRepresentation.setEmail(user.email());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(buildCredentials(user));
        return userRepresentation;
    }

    private List<CredentialRepresentation> buildCredentials(User user) {
        return List.of(
                buildPasswordCredential(user)
        );
    }

    private CredentialRepresentation buildPasswordCredential(User user) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setValue(user.password());
        return credentialRepresentation;
    }
}
