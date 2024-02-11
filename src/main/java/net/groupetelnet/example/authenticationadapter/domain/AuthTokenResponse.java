package net.groupetelnet.example.authenticationadapter.domain;

public record AuthTokenResponse(
        String accessToken,
        String refreshToken
) {
}
