package dk.java.hvz.humansvszombiesbackend.repositories;

import dk.java.hvz.humansvszombiesbackend.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
