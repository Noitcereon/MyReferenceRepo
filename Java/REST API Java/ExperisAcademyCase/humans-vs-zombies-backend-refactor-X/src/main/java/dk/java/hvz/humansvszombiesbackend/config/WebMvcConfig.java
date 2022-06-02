package dk.java.hvz.humansvszombiesbackend.config;

import dk.java.hvz.humansvszombiesbackend.utilities.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserInterceptor userInterceptor;

    public WebMvcConfig(UserInterceptor userInterceptor) {
        super();
        this.userInterceptor = userInterceptor;
    }
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins(
                        "http://localhost:3000", "https://localhost:3000",
                        "http://localhost:5000", "https://localhost:5000",
                        "http://localhost:4173", "https://localhost:4173",
                        "https://dk-hvz-frontend.herokuapp.com/",
                        "https://dk-hvz-keycloak.herokuapp.com/*"
                );
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(userInterceptor);
    }

}
