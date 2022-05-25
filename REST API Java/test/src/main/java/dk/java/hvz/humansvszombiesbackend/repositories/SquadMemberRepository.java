package dk.java.hvz.humansvszombiesbackend.repositories;

import dk.java.hvz.humansvszombiesbackend.models.SquadMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadMemberRepository extends JpaRepository<SquadMember, Long> {
}
