package noitcereon.mydemojavaapi.repositories;

import noitcereon.mydemojavaapi.models.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface IActorRepository extends JpaRepository<Actor, String> {
    Set<Actor> findAllByIsDeletedIsFalse();


    // When Query is there, it overwrites the "derrived" query from the method name.
    // Sub-queries (a.uuid, a.firstName...) are not available in JPQL, but can be used in native.
    @Query(value = "SELECT a FROM Actor a" +
            " LEFT JOIN FETCH a.movies WHERE a.uuid = :uuid AND a.isDeleted = false")
    Actor findByUuidAndIsDeletedIsFalse(@Param("uuid") String uuid);

}