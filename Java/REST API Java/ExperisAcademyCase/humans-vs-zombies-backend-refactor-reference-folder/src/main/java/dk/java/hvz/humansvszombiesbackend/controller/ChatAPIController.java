package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.Chat;
import dk.java.hvz.humansvszombiesbackend.repositories.ChatRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.PlayerRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.SquadRepository;
import dk.java.hvz.humansvszombiesbackend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatAPIController {

    private ChatRepository chatRepository;
    private SquadRepository squadRepository;
    // added user rep for admin to view all chats from both humans and zombies
    private UserRepository userRepository;
    // added player rep to differ between human and zombie chat.
    private PlayerRepository playerRepository;

    public ChatAPIController(ChatRepository chatRepository, SquadRepository squadRepository, UserRepository userRepository, PlayerRepository playerRepository)
    {
        this.chatRepository = chatRepository;
        this.squadRepository = squadRepository;
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    @Operation(summary = "get all chat messages for a game")
    @GetMapping("/api/game/{id}/chat")
    public ResponseEntity<String> getGameChatById(@PathVariable("id") Integer id){
        return null;
    }

    @Operation(summary = "post a new chat message for a game")
    @PostMapping("/api/game/{id}/chat")
    public ResponseEntity<String> postChatMessageInGame(@PathVariable("id") Integer id, @RequestBody Chat chat) {
        return null;
    }

    @Operation(summary = "get all chat messages for a squad/faction in an existing game")
    @GetMapping("/api/game/{id}/squad/{squadId}/chat")
    public ResponseEntity<String> getSquadChatById(@PathVariable("gameId") Integer gameId, @PathVariable("squadId") Integer squadId){
        return null;
    }

    @Operation(summary = "post a new chat messages for a squad in a game")
    @PostMapping("/api/game/{id}/squad/{squadId}/chat")
    public ResponseEntity<String> postChatMessageInSquad(@PathVariable("gameId") Integer gameId, @PathVariable("squadId") Integer squadId, @RequestBody Chat chat) {
        return null;
    }
}
