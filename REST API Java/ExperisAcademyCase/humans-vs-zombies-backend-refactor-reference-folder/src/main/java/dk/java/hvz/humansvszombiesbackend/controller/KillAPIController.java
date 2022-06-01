package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.Game;
import dk.java.hvz.humansvszombiesbackend.models.Kill;
import dk.java.hvz.humansvszombiesbackend.models.Player;
import dk.java.hvz.humansvszombiesbackend.models.responseutils.CommonResponse;
import dk.java.hvz.humansvszombiesbackend.repositories.GameRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.KillRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.PlayerRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "keycloak_implicit")
public class KillAPIController {

    private KillRepository killRepository;
    private GameRepository gameRepository;
    private UserRepository userRepository;
    private PlayerRepository playerRepository;

    public KillAPIController(KillRepository killRepository, GameRepository gameRepository, PlayerRepository playerRepository, UserRepository userRepository) {
        this.killRepository = killRepository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
    }

    @Operation(summary = "get all kills in game")
    @GetMapping("/api/game/{gameId}/kill")
    public ResponseEntity<CommonResponse> getAllKillsFromGame(@PathVariable("gameId") Long id) {
        List<Kill> allKills = killRepository.findAll();
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            List<Kill> killsInGame = allKills.stream().filter(k -> k.getGame().getGame_id() == id).toList();
            return ResponseEntity
                    .ok()
                    .body(new CommonResponse(killsInGame));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "get a specific kill in a game")
    @GetMapping("/api/game/{gameId}/kill/{killId}")
    public ResponseEntity<CommonResponse> getKillWithIDFromGame(@PathVariable("gameId") Long gameId, @PathVariable("killId") Long killId) {
        List<Kill> allKills = killRepository.findAll();
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            List<Kill> killsInGame = allKills.stream().filter(k -> k.getGame().getGame_id() == gameId).toList();
            Optional<Kill> kill = killRepository.findById(killId);
            if (kill.isPresent() && killsInGame.contains(kill.get())) {
                return ResponseEntity
                        .ok()
                        .body(new CommonResponse(kill));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "create a kill within a game")
    @PreAuthorize("hasAnyRole('Administrator', 'User')")
    @PostMapping("/api/game/{gameId}/kill")
    public ResponseEntity<CommonResponse> addKillInGame(@PathVariable("gameId") Long gameId, @RequestBody Kill kill) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {

            Game selectedGame = game.get();
            List<Player> killingPlayer = selectedGame.getPlayers().stream().filter(p -> p.getPlayer_id() == kill.getKiller_id()).toList();
            List<Kill> allKilledPlayers = killRepository.findAll();
            List<Kill> killedPlayersInGame = allKilledPlayers.stream().filter(k -> k.getGame().getGame_id() == selectedGame.getGame_id()).toList();
            List<Player> playerKilled = selectedGame.getPlayers().stream().filter(p -> Objects.equals(p.getBite_code(), kill.getVictim().getBite_code())).toList();

            // check whether killing player exists in the player table
            if(killingPlayer.size() == 0) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Player killer = killingPlayer.get(0);

            // check whether the killer is in fact a zombie
            if (killer.getIs_human().equals("y")) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            //check to see if the killed player exists within the player table.
            if(playerKilled.size() == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Player player = playerKilled.get(0);
            kill.setKiller_id(killer.getPlayer_id());
            kill.setVictim(player);

            // check to see if the killed player exists within selected game
            if (selectedGame.getPlayers().stream().noneMatch(p -> p.getPlayer_id() == player.getPlayer_id())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // check to see if killed players bite code exist in player table
            if (selectedGame.getPlayers().stream().noneMatch(p -> Objects.equals(p.getBite_code(), player.getBite_code()))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // check to see if player with certain bite code hasn't already been registered killed
            if (killedPlayersInGame.stream().anyMatch(k -> Objects.equals(k.getVictim().getBite_code(), player.getBite_code()))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // set default timestamp, if no timestamp, or faulty timestamp is provided as input
            try {
                Timestamp time = kill.getTime_of_death();
            } catch (Exception e) {
                kill.setTime_of_death(new Timestamp(System.currentTimeMillis()));
            }

            // mark a player as a zombie, when he is killed
            player.setIs_human("n");
            playerRepository.save(player);

            // save the new freshly killed player as a victim, as well as in which game the kill happened
            kill.setGame(selectedGame);
            killRepository.save(kill);
            return ResponseEntity
                    .ok()
                    .body(new CommonResponse(kill));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "update a kill")
    @PutMapping("/api/game/{gameId}/kill/{killId}")
    public ResponseEntity<String> updateKillInGame(@PathVariable("gameId") Integer gameId, @PathVariable("killId") Integer killId) {
        return null;
    }

    @Operation(summary = "delete a kill")
    @DeleteMapping("/api/game/{gameId}/kill/{killId}")
    public ResponseEntity<String> deleteKillInGame(@PathVariable("gameId") Integer gameId, @PathVariable("killId") Integer killId) {
        return null;
    }
}
