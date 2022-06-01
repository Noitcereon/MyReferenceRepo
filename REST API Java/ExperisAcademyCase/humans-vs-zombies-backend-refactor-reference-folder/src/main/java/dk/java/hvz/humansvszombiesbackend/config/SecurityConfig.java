package dk.java.hvz.humansvszombiesbackend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashSet;


/*
    Most of this file was written by Greg Linklater (@EternalDeiwos), we did however trim the parts of it,
    we didn't deem necessary for our usage.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // This file was taken from Greg's Hibernate CI Demo project.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Enable CORS -- this is further configured on the controllers
                .cors().and()

                // Sessions will not be used
                .sessionManagement().disable()

                // Disable CSRF -- not necessary when there are no sessions
                .csrf().disable()

                // Disable authorization checks for all requests
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/.well-known/oas", ".well-known/oas/*", "/.well-known/oas/**").permitAll()
                        .antMatchers("/swagger-ui", "/swagger-ui/*", "/swagger-ui/**").permitAll()
                        .antMatchers("/api/game").permitAll()
                        .anyRequest().authenticated())

                .oauth2ResourceServer(oauth2 -> {
                    JwtAuthenticationConverter authnConverter = new JwtAuthenticationConverter();
                    JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();
                    JwtGrantedAuthoritiesConverter roleConverter = new JwtGrantedAuthoritiesConverter();
                    roleConverter.setAuthorityPrefix("ROLE_");
                    roleConverter.setAuthoritiesClaimName("roles");
                    authnConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
                        Collection<GrantedAuthority> scopes = scopeConverter.convert(jwt);
                        Collection<GrantedAuthority> roles = roleConverter.convert(jwt);
                        HashSet<GrantedAuthority> union = new HashSet<>();
                        union.addAll(scopes);
                        union.addAll(roles);

                        for (var a : union) {
                            logger.warn("JWT Authority: {}", a.getAuthority());
                        }

                        return union;
                    });

                    // Enable JWT authentication and access control from JWT claims
                    oauth2.jwt().jwtAuthenticationConverter(authnConverter);
                });
    }

}

