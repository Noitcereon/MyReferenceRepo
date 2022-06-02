package dk.java.hvz.humansvszombiesbackend.repositories;

import dk.java.hvz.humansvszombiesbackend.models.Kill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KillRepository extends JpaRepository<Kill, Long> {
}
