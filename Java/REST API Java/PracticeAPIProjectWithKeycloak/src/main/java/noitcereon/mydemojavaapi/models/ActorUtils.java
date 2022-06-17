package noitcereon.mydemojavaapi.models;

import noitcereon.mydemojavaapi.models.entities.Actor;

import java.util.HashSet;
import java.util.Set;

public class ActorUtils {

    public static boolean isValidAgeRange(int age) {
        return age > 0 && age < 150;
    }

    public static Set<ActorReadonly> convertActorEntitiesToActorReadonlySet(Set<Actor> actorEntities) {
        Set<ActorReadonly> actors = new HashSet<>();
        actorEntities.forEach(actorEntity -> actors.add(new ActorReadonly(actorEntity)));
        return actors;
    }
}
