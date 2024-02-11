package net.groupetelnet.example.authenticationadapter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "keycloak")
public record KeycloakConfig(
    String realm,
    String authUrl,
    String adminClientId,
    String adminClientSecret,
    String applicationClient,
    String applicationClientSecret

){}
