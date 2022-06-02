package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.SquadMember;
import dk.java.hvz.humansvszombiesbackend.repositories.SquadMemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SquadMemberAPIController {

    private SquadMemberRepository squadMemberRepository;

    public SquadMemberAPIController(SquadMemberRepository squadMemberRepository)
    {
        this.squadMemberRepository = squadMemberRepository;
    }

    @Operation(summary = "add a new member to an existing squad")
    @PostMapping("/api/game/{id}/squad/{squadId}/join")
    public ResponseEntity<String> addSquadMemberToSquadInGame(@PathVariable("gameId") Integer gameId, @PathVariable("squadId") Integer squadId, @RequestBody SquadMember squadMember) {
        return null;
    }
}
