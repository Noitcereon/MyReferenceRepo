package dk.java.hvz.humansvszombiesbackend.services;        // Credz: GitLab users:

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import dk.java.hvz.humansvszombiesbackend.models.User;
import dk.java.hvz.humansvszombiesbackend.repositories.UserRepository;

import java.util.Optional;

/*
    A lot of inspiration for this one, was found in another groups work, and adjusted to fit our needs.
    Credits for the original file go to Dennis Massoumnataj @DMasso, Leon-Listo @TheNeonLeon
    and edwineliasson98 @edwineliasson98 (Github)
 */

@Service
public class UserService  {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUserWithJwt(Jwt principal) {
        // parse for firstname, lastname, email and sub
        String sub = principal.getClaimAsString("sub");
        String email = principal.getClaimAsString("email");
        String firstName = principal.getClaimAsString("given_name");
        String lastName = principal.getClaimAsString("family_name");

        // Check if user exists by sub
        Optional<User> user = userRepository.findByKeycloak(sub);

        // If the user exists, return it
        if(user.isPresent()) {
            return user.get();
        }
        // If user doesn't exist, create new user
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setKeycloak(sub);
        newUser.setFirst_name(firstName);
        newUser.setLast_name(lastName);
        // and save it
        try {
            newUser = userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {

            return null;
        }
        return newUser;
    }

}

