package dk.java.hvz.humansvszombiesbackend.repositories;

import dk.java.hvz.humansvszombiesbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByKeycloak(String sub);
}
