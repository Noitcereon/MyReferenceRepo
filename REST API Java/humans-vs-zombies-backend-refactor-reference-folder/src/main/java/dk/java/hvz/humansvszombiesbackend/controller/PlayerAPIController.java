package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.Game;
import dk.java.hvz.humansvszombiesbackend.models.Player;
import dk.java.hvz.humansvszombiesbackend.models.User;
import dk.java.hvz.humansvszombiesbackend.models.responseutils.CommonResponse;
import dk.java.hvz.humansvszombiesbackend.repositories.GameRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.PlayerRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:3000, https://dk-hvz-frontend.herokuapp.com"})
@SecurityRequirement(name = "keycloak_implicit")
@RequestMapping("api")
public class PlayerAPIController {

    private PlayerRepository playerRepository;
    // added this, if we have an admin user, where we need to display some information for non-admin users, and all information for admin users.
    // also needed to check if user is admen, when updating and deleting a player.
    private UserRepository userRepository; // Assuming we get the information about, whether the user is an admin or not.
    private GameRepository gameRepository;

    public PlayerAPIController(PlayerRepository playerRepository, UserRepository userRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Operation(summary = "get all players in game - registered as user")
    //@PreAuthorize("hasRole('User')")
    @GetMapping("/game/{gameId}/player")
    public ResponseEntity<CommonResponse> getAllPlayersFromGame(@PathVariable("gameId") Long gameId) {

        Game game = gameRepository.getById(gameId);
        Set<Player> players = game.getPlayers();

        return ResponseEntity
                .ok()
                .body(new CommonResponse(players));
    }


    @Operation(summary = "get a specific player in a game - registered user")
    @PreAuthorize("hasAnyRole('Administrator', 'User')")
    @GetMapping("/game/{gameId}/player/{playerId}")
    public ResponseEntity<CommonResponse> getPlayerWithIDFromGame(@PathVariable("gameId") Long gameId, @PathVariable("playerId") Long playerId) {

        Optional<Game> gameRepo = gameRepository.findById(gameId);
        if (!gameRepo.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Game game = gameRepo.get();
        boolean isPlayerInGame = game.getPlayers().stream().anyMatch(p -> p.getPlayer_id() == playerId);
        if (isPlayerInGame) {
            Player player = playerRepository.getById(playerId);

            return ResponseEntity
                    .ok()
                    .body(new CommonResponse(player));
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Add an user to a game. He/she will be registered as a player within the game - Admin and Registered Users only.")
    @PreAuthorize("hasAnyRole('Administrator','User')")
    @PostMapping("/user/{userId}/game/{gameId}/player")
    public ResponseEntity<CommonResponse<Player>> addPlayerInGame(@PathVariable("gameId") Long gameId, @PathVariable("userId") Long userId, @RequestBody Player player) {

        Optional<Game> game = gameRepository.findById(gameId);
        Optional<User> user = userRepository.findById(userId);

        // check to see if user is an existing user, and game is an existing game
        if (game.isPresent() && user.isPresent()) {
            Game selectedGame = game.get();
            User currentUser = user.get();

            Set<Player> existingPlayersInAGame = selectedGame.getPlayers();

            // check whether the user is not already included as a player within the game
            if (!existingPlayersInAGame.stream().anyMatch(p -> p.getUserId() == userId)) { // active userID

                // make sure that the added player is not patient zero, if there already exists one in the game.
                if (player.getIs_patient_zero().equals("y") && existingPlayersInAGame.stream().anyMatch(p -> p.getIs_patient_zero().equals("y"))) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                // register that player, so he will be part of a specific game.
                player.setGame(selectedGame);
                // register the specified user (specified by admin user) to the game as a player
                player.setUser(currentUser);



                // generate random bite code for the new player
                String biteCodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                StringBuilder biteCodeBuilder = new StringBuilder();
                Random random = new Random();
                while (biteCodeBuilder.length() < 4) {
                    int index = random.nextInt(0,biteCodeChars.length() - 1);
                    biteCodeBuilder.append(biteCodeChars.charAt(index));
                    // when bite code is build make sure it's unique for the player
                    if (biteCodeBuilder.length() == 4 && existingPlayersInAGame.stream().anyMatch(p -> p.getBite_code() == biteCodeBuilder.toString())) {
                        biteCodeBuilder.setLength(0); // reset bite code, if it isn't unique, and restart the process of building a bite code.
                    }
                }
                player.setBite_code(biteCodeBuilder.toString());

                // add the new player to the list of existing players
                // existingPlayersInAGame.add(player);

                // save the new player to the game and to the player list
                Set<Player> players = currentUser.getPlayers();
                players.add(player);
                currentUser.setPlayers(players);
                userRepository.save(currentUser);
                playerRepository.save(player);

                return ResponseEntity
                        .ok()
                        .body(new CommonResponse<>(player));
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "update a player")
    @PutMapping("/game/{gameId}/player/{playerId}")
    public ResponseEntity<String> updatePlayerInGame(@PathVariable("gameId") Integer gameId, @PathVariable("playerId") Integer playerId) {
        return null;
    }

    @Operation(summary = "delete a player")
    @DeleteMapping("/game/{gameId}/player/{playerId}")
    public ResponseEntity<String> deletePlayerInGame(@PathVariable("gameId") Integer gameId, @PathVariable("playerId") Integer playerId) {
        return null;
    }
}
