package noitcereon.mydemojavaapi.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(info = @Info(title = "Java Demo Api",
        description = "An API made to renew or attain knowledge of Java Api with Spring boot w. Hibernate + Keycloak",
        version = "0.1.0")
)
// https://github.com/OAI/OpenAPI-Specification/blob/main/versions/3.1.0.md#securitySchemeObject
// The link above contains the specification for all things OpenAPI. SecurityScheme included.
@SecurityScheme(name = "keycloak_implicit", type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        // https://swagger.io/docs/specification/authentication/openid-connect-discovery/
                        // All openid-connect compliant Identity Providers publish a "discovery document".
                        // The path of the document is relative to the issuer (iss claim) in the JWT.
                        // Typically: https://{provider-uri}/.well-known/openid-configuration
                        // For my local instance of keycloak it is at:
                        // http://localhost:8083/realms/renewing-java-api-knowledge/.well-known/openid-configuration
                        authorizationUrl = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/auth",
                        tokenUrl = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/token",
                        scopes = {
                                // These scopes are defined in the Keycloak Server.
                                // They specify what information comes with the JWT
                                @OAuthScope(name = "openid"),
                                @OAuthScope(name = "profile"),
                                @OAuthScope(name = "email"),
                                @OAuthScope(name = "groups"),
                        }
)))
@Configuration
public class OpenApiConfig {
    private final Logger _logger = LoggerFactory.getLogger(getClass());
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String _issuerUri;
    @Bean(name = "initializeOpenApiConfig")
    public void initializeOpenApiConfig(){
        _logger.debug("Initialing OpenApiConfig");
        _logger.debug("Issuer Uri = " + _issuerUri);
    }
}
