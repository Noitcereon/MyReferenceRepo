package noitcereon.mydemojavaapi.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Genre {
    @Id
    private final String uuid;
    private String name;
    private LocalDateTime createdOn;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryGenre")
    private final Set<Movie> movies = new HashSet<>();

    @ColumnDefault("false")
    private boolean isDeleted;

    public Genre() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Genre(String name) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.createdOn = LocalDateTime.now();
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}
