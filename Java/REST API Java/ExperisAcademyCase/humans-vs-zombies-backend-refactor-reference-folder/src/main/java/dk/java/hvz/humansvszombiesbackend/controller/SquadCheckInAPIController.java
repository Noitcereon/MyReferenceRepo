package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.Chat;
import dk.java.hvz.humansvszombiesbackend.repositories.SquadCheckinRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.SquadMemberRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.SquadRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SquadCheckInAPIController {

    private SquadCheckinRepository squadCheckinRepository;
    // either use squad rep or squad member rep to assert a checkin by a squad.
    private SquadRepository squadRepository; // or private SquadMemberRepository squadMemberRepository;

    public SquadCheckInAPIController(SquadCheckinRepository squadCheckinRepository, SquadRepository squadRepository)
    {
        this.squadCheckinRepository = squadCheckinRepository;
        this.squadRepository = squadRepository;
    }

    @Operation(summary = "get all check-ins for a squad within a game")
    @GetMapping("/api/game/{id}/squad/{squadId}/check-in")
    public ResponseEntity<String> getSquadCheckInById(@PathVariable("gameId") Integer gameId, @PathVariable("squadId") Integer squadId){
        return null;
    }

    @Operation(summary = "create a squad check-in")
    @PostMapping("/api/game/{id}/squad/{squadId}/check-in")
    public ResponseEntity<String> addSquadCheckIn(@PathVariable("gameId") Integer gameId, @PathVariable("squadId") Integer squadId, @RequestBody Chat chat) {
        return null;
    }
}
