package noitcereon.mydemojavaapi.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashSet;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
// Enables the use of @PreAuthorize & @Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.trace("Starting Security Configuration");
        http
                // Enable CORS -- this is configured on controllers with @CrossOrigin
                .cors().and()

                // Sessions will not be used
                .sessionManagement().disable()

                // Disable Cross Site Request Forgery tokens - They are not needed when there are no sessions.
                .csrf().disable()

                // Enable security for http requests
                .authorizeRequests(authorize -> {
                    authorize
                            // Specify paths where public access is allowed
                            // public Swagger paths
                            .antMatchers("/swagger-ui/**").permitAll()
                            .antMatchers("/swagger").permitAll()
                            .antMatchers("/v3/api-docs/**").permitAll()

                            // Other public paths
                            .antMatchers("/").permitAll()
                            .antMatchers("/favicon.ico").permitAll()

                            // All remaining paths require authentication
                            .anyRequest().authenticated();
                })
                // Configure OAuth2 Resource Server (JWT authentication) tentative note: gives @PreAuthorize more options
                .oauth2ResourceServer(oauth2 -> {
                    // Used to convert JWT to AbstractAuthenticationToken
                    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();

                    // Groups and roles need to be configured in Keycloak (Mapping to claims 'roles' & 'groups')
                    // Convert role claims on the JWT into GrantedAuthorities
                    JwtGrantedAuthoritiesConverter roleConverter = new JwtGrantedAuthoritiesConverter();
                    roleConverter.setAuthorityPrefix("ROLE_");
                    roleConverter.setAuthoritiesClaimName("roles");
                    // Convert group claims on the JWT into GrantedAuthorities
                    JwtGrantedAuthoritiesConverter groupConverter = new JwtGrantedAuthoritiesConverter();
                    groupConverter.setAuthorityPrefix("GROUP_");
                    groupConverter.setAuthoritiesClaimName("groups");
                    // Convert scope claims on the JWT into GrantedAuthorities
                    JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();

                    // Set the jwtConverters JwtGrantedAuthoritiesConverter object
                    jwtConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
                        // Converts the JWTs roles, groups and scopes fields into GrantedAuthorities (which can be used
                        // when checking if the provided jwt has various kinds of authorization.

                        // This will read the 'roles' claim you configured above
                        // jwt["roles"] -> new GrantedAuthority("ROLE_roleName")
                        Collection<GrantedAuthority> roles = roleConverter.convert(jwt);

                        // This will read the 'groups' claim you configured above
                        // jwt["groups"] -> new GrantedAuthority("GROUP_groupName")
                        Collection<GrantedAuthority> groups = groupConverter.convert(jwt);

                        Collection<GrantedAuthority> scopes = scopeConverter.convert(jwt);
                        HashSet<GrantedAuthority> mergedAuthorities = new HashSet<>();
                        mergedAuthorities.addAll(roles);
                        mergedAuthorities.addAll(groups);
                        mergedAuthorities.addAll(scopes);
                        for (GrantedAuthority authority : mergedAuthorities) {
                            logger.debug(String.format("GrantedAuthority: %s", authority));
                        }
                        return mergedAuthorities;
                    });

                    // Tell the oauth2 config to use the jwtConverter we just configured.
                    // This enables JWT authentication and access control from JWT claims
                    oauth2.jwt().jwtAuthenticationConverter(jwtConverter);
                });
        logger.trace("Finished Security Configuration");
    }
}
