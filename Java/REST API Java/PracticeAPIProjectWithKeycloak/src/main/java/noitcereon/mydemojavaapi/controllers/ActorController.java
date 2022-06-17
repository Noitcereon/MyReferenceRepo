package noitcereon.mydemojavaapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import noitcereon.mydemojavaapi.models.ActorPostModel;
import noitcereon.mydemojavaapi.models.ActorReadonly;
import noitcereon.mydemojavaapi.models.ActorUtils;
import noitcereon.mydemojavaapi.models.entities.Actor;
import noitcereon.mydemojavaapi.repositories.IActorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/actors")
@CrossOrigin(origins = {"http://localhost:3000"})
@SecurityRequirement(name = "keycloak_implicit") // This should match the name given in OpenApiConfig
public class ActorController {
    private final IActorRepository actorRepo;

    public ActorController(IActorRepository actorRepository) {
        actorRepo = actorRepository;
    }

    @PostMapping
    public ResponseEntity<ActorReadonly> createActor(@RequestBody ActorPostModel actor) {
        Actor actorEntity = new Actor(actor);
        Actor savedObject = actorRepo.save(actorEntity);
        ActorReadonly readonlyActor = new ActorReadonly(savedObject);
        return ResponseEntity.ok(readonlyActor);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('GROUP_standard', 'ROLE_standard')")
    public ResponseEntity<Set<ActorReadonly>> getAll() {
        Set<Actor> actorEntities = actorRepo.findAllByIsDeletedIsFalse();
        if (actorEntities.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        Set<ActorReadonly> actors = ActorUtils.convertActorEntitiesToActorReadonlySet(actorEntities);
        return ResponseEntity.ok(actors);
    }

    @GetMapping("{actorId}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ActorReadonly> getById(@PathVariable String actorId) {
        Actor actorEntity = actorRepo.findByUuidAndIsDeletedIsFalse(actorId);
        if(actorEntity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ActorReadonly(actorEntity));
    }
    @Operation(description = "Updates the actor with the content of the body, if the actor exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Actor with that id does not exist."),
            @ApiResponse(responseCode = "200", description = "Successfully updated Actor.")
    })
    @PutMapping("{actorId}")
    public ResponseEntity<ActorReadonly> updateActor(@RequestBody ActorPostModel updatedActor, @PathVariable String actorId) {
        Actor actorEntityToUpdate = actorRepo.findByUuidAndIsDeletedIsFalse(actorId);
        if (actorEntityToUpdate == null) return ResponseEntity.notFound().build();
        actorEntityToUpdate.setFirstName(updatedActor.getFirstName());
        actorEntityToUpdate.setLastName(updatedActor.getLastName());
        actorEntityToUpdate.setAge(updatedActor.getAge());
        Actor savedActorEntity = actorRepo.save(actorEntityToUpdate);
        return ResponseEntity.ok(new ActorReadonly(savedActorEntity));
    }

    @DeleteMapping("{actorId}")
    public ResponseEntity<ActorReadonly> disableActor(@PathVariable String actorId) {
        Optional<Actor> actorOptional = actorRepo.findById(actorId);
        if (actorOptional.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Actor actorToDisable = actorOptional.get();
        actorToDisable.setDeleted(true);
        Actor savedActorEntity = actorRepo.save(actorToDisable);
        return ResponseEntity.ok(new ActorReadonly(savedActorEntity));
    }
}
