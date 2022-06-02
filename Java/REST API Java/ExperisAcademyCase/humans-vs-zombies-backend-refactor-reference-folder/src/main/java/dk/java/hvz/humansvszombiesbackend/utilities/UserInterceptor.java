package dk.java.hvz.humansvszombiesbackend.utilities;

import dk.java.hvz.humansvszombiesbackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    This file was again from a fellow groups work. It fit out needs, so we "borrowed" a lot of their code,
    and then trimmed it and edited it, to once again fit our needs.
    Credits go to: Dennis Massoumnataj @DMasso, Leon-Listo @TheNeonLeon and edwineliasson98 @edwineliasson98 (GitHub)
 */

@Component
public class UserInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(UserInterceptor.class);

    private final UserService userService;

    public UserInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[preHandle][" + request + "][" + request.getMethod() + "]" + request.getRequestURI());

        // check if user is authenticated
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            Jwt principal = (Jwt) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            log.warn("[preHandle][" + principal + "]");
            // create user
            userService.createUserWithJwt(principal);
            return true;
        }

        return true;
    }

}

