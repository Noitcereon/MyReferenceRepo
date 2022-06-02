package dk.java.hvz.humansvszombiesbackend.repositories;

import dk.java.hvz.humansvszombiesbackend.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
