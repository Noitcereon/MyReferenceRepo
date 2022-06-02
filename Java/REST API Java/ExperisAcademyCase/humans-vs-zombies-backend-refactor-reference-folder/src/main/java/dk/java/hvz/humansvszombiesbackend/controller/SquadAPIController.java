package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.Chat;
import dk.java.hvz.humansvszombiesbackend.models.Player;
import dk.java.hvz.humansvszombiesbackend.models.Squad;
import dk.java.hvz.humansvszombiesbackend.models.SquadMember;
import dk.java.hvz.humansvszombiesbackend.repositories.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SquadAPIController {

    private SquadRepository squadRepository;
    private SquadMemberRepository squadMemberRepository;
    private UserRepository userRepository;

    public SquadAPIController(SquadRepository squadRepository, SquadMemberRepository squadMemberRepository, UserRepository userRepository) {
        this.squadRepository = squadRepository;
        this.squadMemberRepository = squadMemberRepository;
        this.userRepository = userRepository;
    }

    @Operation(summary = "get all squads in game")
    @GetMapping("/api/game/{id}/squad")
    public ResponseEntity<String> getAllSquadsFromGame(@PathVariable("id") Integer id) {
        return null;
    }

    @Operation(summary = "get a specific squad in a game")
    @GetMapping("/api/game/{gameId}/squad/{squadId}")
    public ResponseEntity<String> getSquadWithIDFromGame(@PathVariable("gameId") Integer gameId, @PathVariable("squadId") Integer squadId) {
        return null;
    }

    @Operation(summary = "create a new squad within a game")
    @PostMapping("/api/game/{id}/squad")
    public ResponseEntity<String> addSquadInGame(@PathVariable("id") Integer id, @RequestBody Squad squad) {
        return null;
    }

    @Operation(summary = "update a squad")
    @PutMapping("/api/game/{gameId}/squad/{squadId}")
    public ResponseEntity<String> updateSquadInGame(@PathVariable("gameId") Integer gameId, @PathVariable("squadId") Integer squadId, @RequestBody Squad squad) {
        return null;
    }

    @Operation(summary = "delete a squad")
    @DeleteMapping("/api/game/{gameId}/squad/{squadId}")
    public ResponseEntity<String> deleteSquadInGame(@PathVariable("gameId") Integer gameId, @PathVariable("squadId") Integer squadId) {
        return null;
    }
}
