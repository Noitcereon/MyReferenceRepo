package dk.java.hvz.humansvszombiesbackend.controller;

import dk.java.hvz.humansvszombiesbackend.models.Player;
import dk.java.hvz.humansvszombiesbackend.models.User;
import dk.java.hvz.humansvszombiesbackend.models.responseutils.CommonResponse;
import dk.java.hvz.humansvszombiesbackend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000, https://dk-hvz-frontend.herokuapp.com"})
@SecurityRequirement(name = "keycloak_implicit")
@RequestMapping("api")
public class UserAPIController {

    private UserRepository userRepository;

    public UserAPIController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Get all users")
    @PreAuthorize("hasRole('Administrator')")
    @GetMapping("/admin/users")
    public ResponseEntity<CommonResponse<List<User>>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(new CommonResponse<>(users));
    }

    @Operation(summary = "get information on user")
    @GetMapping("/user/{id}")
    public ResponseEntity<String> getUser(@PathVariable("id") Integer id) {
        return null;
    }

    @Operation(summary = "add new user")
    @PostMapping("/user/{id}")
    public ResponseEntity<String> addUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return null;
    }

    @Operation(summary = "update existing user")
    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Integer id) {
        return null;
    }

    @Operation(summary = "deletes user")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        return null;
    }
}
