package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.Mission;
import dk.java.hvz.humansvszombiesbackend.models.Player;
import dk.java.hvz.humansvszombiesbackend.repositories.MissionRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.PlayerRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MissionAPIController {

    private MissionRepository missionRepository;
    // only shows mission, if the player is a part of the correct faction
    private PlayerRepository playerRepository;
    // used for admin only operations
    private UserRepository userRepository;

    public MissionAPIController(MissionRepository missionRepository, PlayerRepository playerRepository, UserRepository userRepository) {
        this.missionRepository = missionRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
    }

    @Operation(summary = "get all missions in game")
    @GetMapping("/api/game/{id}/mission")
    public ResponseEntity<String> getAllMissionsFromGame(@PathVariable("id") Integer id) {
        return null;
    }

    @Operation(summary = "get a specific mission in a game")
    @GetMapping("/api/game/{gameId}/mission/{missionId}")
    public ResponseEntity<String> getMissionWithIDFromGame(@PathVariable("gameId") Integer gameId, @PathVariable("missionId") Integer missionId) {
        return null;
    }

    @Operation(summary = "create a new mission in a game")
    @PostMapping("/api/game/{id}/mission")
    public ResponseEntity<String> addMissionInGame(@PathVariable("id") Integer id, @RequestBody Mission mission) {
        return null;
    }

    @Operation(summary = "update a mission")
    @PutMapping("/api/game/{gameId}/mission/{missionId}")
    public ResponseEntity<String> updateMissionInGame(@PathVariable("gameId") Integer gameId, @PathVariable("missionId") Integer missionId, @RequestBody Mission mission) {
        return null;
    }

    @Operation(summary = "delete a mission")
    @DeleteMapping("/api/game/{gameId}/mission/{missionId}")
    public ResponseEntity<String> deleteMissionInGame(@PathVariable("gameId") Integer gameId, @PathVariable("missionId") Integer missionId) {
        return null;
    }
}
