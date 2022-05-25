package dk.java.hvz.humansvszombiesbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dk.java.hvz.humansvszombiesbackend.controller.GameState;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long game_id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String game_state;

    @Column
    private String game_description;

    @Column
    private double nw_lat;

    @Column
    private double nw_lng;

    @Column
    private double se_lat;

    @Column
    private double se_lng;

    @Column(columnDefinition = "TEXT")
    private String rules;

    @OneToMany(mappedBy = "game")
    private Set<Player> players = new HashSet<>();

    @OneToMany(mappedBy = "game")
    private Set<Kill> kills = new HashSet<>();

    @OneToMany(mappedBy = "game")
    private Set<Mission> missions = new HashSet<>();

    @OneToMany(mappedBy = "game")
    private Set<Squad> squads = new HashSet<>();

    @OneToMany(mappedBy = "game")
    private Set<Chat> chats = new HashSet<>();

    @JsonIgnore
    public Set<Player> getPlayers() {
        return players;
    }

    @JsonIgnore
    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @JsonIgnore
    public Set<Kill> getKills() {
        return kills;
    }

    @JsonIgnore
    public void setKills(Set<Kill> kills) {
        this.kills = kills;
    }

    @JsonIgnore
    public Set<Mission> getMissions() {
        return missions;
    }

    @JsonIgnore
    public void setMissions(Set<Mission> missions) {
        this.missions = missions;
    }

    @JsonIgnore
    public Set<Squad> getSquads() {
        return squads;
    }

    @JsonIgnore
    public void setSquads(Set<Squad> squads) {
        this.squads = squads;
    }

    @JsonIgnore
    public Set<Chat> getChats() {
        return chats;
    }

    @JsonIgnore
    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public long getGame_id() {
        return game_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame_state() {
        return game_state;
    }

    public void setGame_state(String game_state) {
        String gameStateLowerCase = game_state.toLowerCase(Locale.ROOT);
        if(this.game_state == null) {
            this.game_state = GameState.Registration.toString();
            return;
        }
        // check for invalid input (valid: registration, in progress, completed)
        if (!gameStateLowerCase.equals(GameState.Registration.toString()) && !gameStateLowerCase.equals(GameState.InProgress.toString()) && !gameStateLowerCase.equals(GameState.Completed.toString())){
            return;
        }
        this.game_state = game_state;
    }

    public String getGame_description() {
        return game_description;
    }

    public void setGame_description(String game_description) {
        this.game_description = game_description;
    }

    public double getNw_lat() {
        return nw_lat;
    }

    public void setNw_lat(double nw_lat) {
        this.nw_lat = nw_lat;
    }

    public double getNw_lng() {
        return nw_lng;
    }

    public double getSe_lat() {
        return se_lat;
    }

    public void setSe_lat(double se_lat) {
        this.se_lat = se_lat;
    }

    public double getSe_lng() {
        return se_lng;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

}
