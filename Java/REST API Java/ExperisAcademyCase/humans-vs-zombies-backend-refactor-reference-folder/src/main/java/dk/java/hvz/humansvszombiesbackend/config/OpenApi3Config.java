package dk.java.hvz.humansvszombiesbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/*
        This file was originally written Greg Linklater (@EternalDeiwos) and edited by
        us, to make it fit our needs.
 */

@OpenAPIDefinition(info = @Info(title = "Humans VS Zombies API", description = "An API to support the frontend for HvZ", version = "0.2.0"))
@SecurityScheme(name = "keycloak_implicit", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(authorizationCode =
        @OAuthFlow(authorizationUrl = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/auth",
                tokenUrl = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/token", scopes = {
                @OAuthScope(name = "openid", description = "OpenID Connect Endpoints"),
                @OAuthScope(name = "profile", description = "Standard profile claims excluding phone and email")
        }))
)
public class OpenApi3Config {}
