package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.User;
import dk.java.hvz.humansvszombiesbackend.models.responseutils.CommonResponse;
import dk.java.hvz.humansvszombiesbackend.repositories.UserRepository;
import dk.java.hvz.humansvszombiesbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@SecurityRequirement(name = "keycloak_implicit")
@RequestMapping("api")
public class LoginAPIController {

    private final UserRepository userRepository;
    private final UserService userService;

    public LoginAPIController(UserRepository userRepository) {
        this.userRepository = userRepository;
        userService = new UserService(this.userRepository);
    }

    @Operation(summary = "A simple login function, that should not called directly by any user.")
    @GetMapping("/login")
    public ResponseEntity<CommonResponse<User>> loginOrCreateUser(@AuthenticationPrincipal Jwt principal) {
        User user = null;
        Optional<User> userOptional = userRepository.findByKeycloak(principal.getClaimAsString("sub"));
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = userService.createUserWithJwt(principal);
        }
        return ResponseEntity.ok().body(new CommonResponse<>(user));
    }
}
