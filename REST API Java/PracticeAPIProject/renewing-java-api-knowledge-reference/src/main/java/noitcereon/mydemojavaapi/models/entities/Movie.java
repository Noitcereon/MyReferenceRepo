package noitcereon.mydemojavaapi.models.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Movie {
    @Id
    @Column(length = 36)
    private String uuid;
    private String title;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar releaseYear;
    private int durationInMinutes;

    @ColumnDefault("false")
    private boolean isDeleted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre primaryGenre = new Genre();

    // When using ManyToMany you use @JoinTable to specify both the name of the table and
    // the columns in the joined table (inverseJoinColumns is taken from the one where mappedBy is present)
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "movie_actor", joinColumns = {@JoinColumn(name = "movie_id")}, inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    private Set<Actor> actors = new HashSet<>();

    public Movie() {
    }

    public Movie(String uuid, String title, int releaseYear, int durationInMinutes, Set<Actor> actors, String genre) {
        setUuid(uuid);
        setTitle(title);
        setReleaseYear(releaseYear);
        setDurationInMinutes(durationInMinutes);
        setActors(actors);
        this.isDeleted = false;
        this.primaryGenre.setName(genre);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        if (uuid.length() == 36) {
            this.uuid = uuid;
        } else {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        if (releaseYear > 9999) {
            this.releaseYear = Calendar.getInstance();
            this.releaseYear.set(Calendar.YEAR, 0);
            return;
        }
        Calendar specificCalendarYear = Calendar.getInstance();
        specificCalendarYear.set(Calendar.YEAR, releaseYear);
        this.releaseYear = specificCalendarYear;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @JsonGetter(value = "actors")
    public Set<String> getActorEndpoints() {
        if (actors == null) return null;
        return actors.stream().map(actor ->
                        String.format("'%s %s', /api/actors/%s",
                                actor.getFirstName(), actor.getLastName(), actor.getUuid()))
                .collect(Collectors.toSet());
    }

    @JsonIgnore
    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Genre getPrimaryGenre() {
        return primaryGenre;
    }

    public void setPrimaryGenre(Genre primaryGenre) {
        this.primaryGenre = primaryGenre;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getDurationInMinutes() == movie.getDurationInMinutes() && getUuid().equals(movie.getUuid()) && getTitle().equals(movie.getTitle()) && getReleaseYear().equals(movie.getReleaseYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getTitle(), getReleaseYear(), getDurationInMinutes());
    }
}
