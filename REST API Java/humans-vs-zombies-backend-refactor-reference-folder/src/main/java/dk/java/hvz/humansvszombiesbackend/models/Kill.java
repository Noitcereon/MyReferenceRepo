package dk.java.hvz.humansvszombiesbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Kill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long kill_id;

    @Column(nullable = false)
    private Timestamp time_of_death;

    @Column(columnDefinition = "TEXT")
    private String story;

    @Column
    private double lat;

    @Column
    private double lng;

    @Column(nullable = false)
    private long killer_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private Game game;

    public long getKiller_id() {
        return killer_id;
    }

    public void setKiller_id(long killer_id) {
        this.killer_id = killer_id;
    }

    public Player getVictim() {
        return player;
    }

    public void setVictim(Player victim) {
        this.player = victim;
    }

    @JsonIgnore
    public Game getGame() {
        return game;
    }

    @JsonIgnore
    public void setGame(Game game) {
        this.game = game;
    }

    @JsonIgnore
    public long getKill_id() {
        return kill_id;
    }

    @JsonIgnore
    public void setKill_id(long kill_id) {
        this.kill_id = kill_id;
    }

    public Timestamp getTime_of_death() {
        return time_of_death;
    }

    public void setTime_of_death(Timestamp time_of_death) {
        this.time_of_death = time_of_death;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
