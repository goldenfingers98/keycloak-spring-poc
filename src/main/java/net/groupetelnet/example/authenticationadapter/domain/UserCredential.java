package net.groupetelnet.example.authenticationadapter.domain;

import java.io.Serializable;

public record UserCredential(
        String username,
        String password
) implements Serializable {}
