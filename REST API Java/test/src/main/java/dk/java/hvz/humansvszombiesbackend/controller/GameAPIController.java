package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.responseutils.CommonResponse;
import dk.java.hvz.humansvszombiesbackend.models.Game;
import dk.java.hvz.humansvszombiesbackend.repositories.GameRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000, https://dk-hvz-frontend.herokuapp.com"})
@SecurityRequirement(name = "keycloak_implicit")
@RequestMapping("api")
public class GameAPIController {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameAPIController(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Get all existing games - Available to everyone")
    @GetMapping("/game")
    public ResponseEntity<CommonResponse<List<Game>>> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return ResponseEntity
                .ok()
                .body(new CommonResponse<>(games));
    }

    @Operation(summary = "Get a specific game by id as PathVariable - Registered users and Admin only")
    @PreAuthorize("hasRole('User')")
    @GetMapping("/game/{id}")
    public ResponseEntity<CommonResponse<Game>> getGameById(@PathVariable("id") Long gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isPresent()) {
            return ResponseEntity
                    .ok()
                    .body(new CommonResponse<>(game.get()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new game - Admin only")
    @PreAuthorize("hasRole('Administrator')")
    @PostMapping("/admin/game")
    public ResponseEntity<CommonResponse<Game>> createGame(@RequestBody Game game) {
        // fix needed to re-set the game state to get the enum types to register a valid option.
        game.setGame_state(game.getGame_state());
        Game createdGame = gameRepository.save(game);
        return ResponseEntity
                .ok()
                .body(new CommonResponse<>(createdGame));
    }


    @Operation(summary = "Update an existing game by id as PathVariable - Admin only.")
    @PreAuthorize("hasRole('Administrator')")
    @PutMapping("/admin/game/{id}")
    public ResponseEntity<String> updateGameById(@PathVariable("id") Long gameId, @RequestBody Game game) {
        if (gameRepository.existsById(gameId) == false) {
            return ResponseEntity.notFound().build();
        }
        Game savedGame = gameRepository.save(game);
        return ResponseEntity.ok(savedGame.getGame_state());
    }

    @Operation(summary = "Updates only game state by id as PathVariable. Possible states: registration, in progress, complete - Admin only")
    @PreAuthorize("hasRole('Administrator')")
    @PatchMapping("/admin/game/{gameId}")
    public ResponseEntity<CommonResponse<String>> updateGameStateByGameId(@PathVariable("gameId") Long gameId, @RequestBody String newState) {
        if (gameRepository.existsById(gameId) == false) {
            return ResponseEntity.notFound().build();
        }
        String newStateLowerCase = newState.toLowerCase(Locale.ROOT);
        // If it is NOT a game state it will return bad request.
        if (!newStateLowerCase.equals(GameState.Registration.toString()) && !newStateLowerCase.equals(GameState.InProgress.toString()) && !newStateLowerCase.equals(GameState.Completed.toString())){
            return ResponseEntity.badRequest().build();
        }
        Game game = gameRepository.getById(gameId);
        game.setGame_state(newStateLowerCase);
        gameRepository.save(gameRepository.getById(gameId));
        return ResponseEntity.ok(new CommonResponse<>(newStateLowerCase));
    }


    @Operation(summary = "Delete a game by id as PathVariable - Admin only.")
    @PreAuthorize("hasRole('Administrator')")
    @DeleteMapping("/game/{id}")
    public ResponseEntity<String> deleteGameById(@PathVariable("id") Long gameId) {
        return null;
    }

}
