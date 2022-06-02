package dk.java.hvz.humansvszombiesbackend.repositories;

import dk.java.hvz.humansvszombiesbackend.models.SquadCheckin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadCheckinRepository extends JpaRepository<SquadCheckin, Long> {
}
