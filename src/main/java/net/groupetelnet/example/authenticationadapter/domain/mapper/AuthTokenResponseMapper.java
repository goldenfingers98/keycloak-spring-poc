package net.groupetelnet.example.authenticationadapter.domain.mapper;

import net.groupetelnet.example.authenticationadapter.domain.AuthTokenResponse;
import org.keycloak.representations.AccessTokenResponse;

public class AuthTokenResponseMapper {

    public AuthTokenResponse toAuthTokenResponse(AccessTokenResponse accessTokenResponse) {
        if (accessTokenResponse == null) {
            return null;
        }
        return new AuthTokenResponse(
                accessTokenResponse.getToken(),
                accessTokenResponse.getRefreshToken()
        );
    }
}
