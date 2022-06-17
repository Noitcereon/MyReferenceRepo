package noitcereon.mydemojavaapi.models;

import noitcereon.mydemojavaapi.models.entities.Actor;
import noitcereon.mydemojavaapi.models.entities.Movie;

import java.util.Set;

public class ActorReadonly{
    public final String uuid;
    public final String firstName;
    public final String lastName;
    public final int age;
    public final Set<Movie> movies;

    public ActorReadonly(Actor actorEntity){
        this.uuid = actorEntity.getUuid();
        this.firstName = actorEntity.getFirstName();
        this.lastName = actorEntity.getLastName();
        this.age = actorEntity.getAge();
        this.movies = actorEntity.getMovies();
    }
}