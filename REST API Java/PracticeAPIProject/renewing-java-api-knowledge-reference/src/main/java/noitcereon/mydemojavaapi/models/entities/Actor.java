package noitcereon.mydemojavaapi.models.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import noitcereon.mydemojavaapi.models.ActorPostModel;
import noitcereon.mydemojavaapi.models.ActorUtils;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Actor {

    @Id
    @Column(length = 36)
    private String uuid;
    private String firstName;
    private String lastName;
    private int age;

    @ColumnDefault("false")
    private boolean isDeleted;

    // The "mappedBy" tells Hibernate, which private field to use in ORM mapping (exists in the related model).
    // In this case the private field variable name is actors (see Movie model)
    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

    public Actor() {
    }

    public Actor(String uuid, String firstName, String lastName, int age, Set<Movie> movies) {
        setUuid(uuid);
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setMovies(movies);
    }
    public Actor(ActorPostModel actor){
        setUuid(UUID.randomUUID().toString());
        setFirstName(actor.getFirstName());
        setLastName(actor.getLastName());
        setAge(actor.getAge());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        if (uuid.length() == 36) {
            this.uuid = uuid;
            return;
        }
        this.uuid = UUID.randomUUID().toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (ActorUtils.isValidAgeRange(age)) {
            this.age = age;
        }
    }

    @JsonIgnore
    public Set<Movie> getMovies() {
        return movies;
    }

    // The value attribute in the JsonGetter specifies the name of the property when it is displayed in JSON
    @JsonGetter(value = "movies")
    public Set<String> getMovieEndpoints() {
        return movies.stream().map(movie -> String.format("'%s', /api/movies/%s", movie.getTitle(), movie.getUuid())).collect(Collectors.toSet());
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
