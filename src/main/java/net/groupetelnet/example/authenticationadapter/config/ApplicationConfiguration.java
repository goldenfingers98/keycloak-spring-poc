package net.groupetelnet.example.authenticationadapter.config;

import net.groupetelnet.example.authenticationadapter.domain.mapper.AuthTokenResponseMapper;
import net.groupetelnet.example.authenticationadapter.domain.mapper.UserMapper;
import net.groupetelnet.example.authenticationadapter.service.AuthService;
import net.groupetelnet.example.authenticationadapter.service.AuthServiceImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

    private Keycloak getKeycloakAdminClientConfig(KeycloakConfig keycloakConfig) {
        return getKeycloakDefaultRealmBuilder(keycloakConfig)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakConfig.adminClientId())
                .clientSecret(keycloakConfig.adminClientSecret())
                .build();
    }

    private KeycloakBuilder getKeycloakDefaultRealmBuilder(KeycloakConfig keycloakConfig) {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakConfig.authUrl())
                .realm(keycloakConfig.realm());
    }

    @Bean
    @Scope("prototype")
    public KeycloakBuilder getApplicationClientKeycloakBuilderPrototype(KeycloakConfig keycloakConfig) {
        return getKeycloakDefaultRealmBuilder(keycloakConfig)
                .clientId(keycloakConfig.applicationClient())
                .clientSecret(keycloakConfig.applicationClientSecret());
    }


    @Bean
    public UsersResource getUsersResource(KeycloakConfig keycloakConfig) {
        return getKeycloakAdminClientConfig(keycloakConfig)
                .realm(keycloakConfig.realm())
                .users();
    }

    @Bean
    public AuthTokenResponseMapper getAuthTokenResponseMapper() {
        return new AuthTokenResponseMapper();
    }

    @Bean
    public UserMapper getUserMapper() {
        return new UserMapper();
    }

    @Bean
    public AuthService getAuthService() {
        return new AuthServiceImpl();
    }

}
