package net.groupetelnet.example.authenticationadapter.domain;

public record User(
        String id,
        String firstname,
        String lastname,
        String username,
        String email,
        String password
) {
}
