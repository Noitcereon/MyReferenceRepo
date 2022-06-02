package noitcereon.mydemojavaapi.repositories;

import noitcereon.mydemojavaapi.models.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IGenreRepository extends JpaRepository<Genre, String> {
    Set<Genre> findAllByIsDeletedIsFalse();
    Genre findByUuidAndIsDeletedIsFalse(String uuid);

    boolean existsGenreByName(String name);

    Genre findByName(String name);
}
