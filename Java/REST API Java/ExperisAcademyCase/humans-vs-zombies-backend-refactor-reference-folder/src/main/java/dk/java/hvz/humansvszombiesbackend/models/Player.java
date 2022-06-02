package dk.java.hvz.humansvszombiesbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long player_id;

    @Column(length = 1, nullable = false)
    private String is_human;

    @Column(length = 1, nullable = false)
    private String is_patient_zero;

    @Column(unique = true, nullable = false)
    private String bite_code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "player")
    private Set<SquadMember> squadMembers = new HashSet<>();

    @OneToMany(mappedBy = "player")
    private Set<Kill> kills = new HashSet<>();

    @OneToMany(mappedBy = "player")
    private Set<Chat> messages = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private Game game;

    public long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(long player_id) {
        this.player_id = player_id;
    }

    public String getIs_human() {
        return is_human;
    }

    public void setIs_human(String is_human) {
        this.is_human = is_human;
    }

    public String getIs_patient_zero() {
        return is_patient_zero;
    }

    public void setIs_patient_zero(String is_patient_zero) {
        this.is_patient_zero = is_patient_zero;
    }


    public String getBite_code() {
        return bite_code;
    }


    public void setBite_code(String bite_code) {
        this.bite_code = bite_code;
    }

    @JsonGetter("user_id")
    public long getUserId() {
        return user.getUser_id();
    }

    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public Set<SquadMember> getSquadMembers() {
        return squadMembers;
    }

    @JsonIgnore
    public void setSquadMembers(Set<SquadMember> squadMembers) {
        this.squadMembers = squadMembers;
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
    public Set<Chat> getMessages() {
        return messages;
    }

    @JsonIgnore
    public void setMessages(Set<Chat> messages) {
        this.messages = messages;
    }

    @JsonGetter("game_id")
    public long getGameId() {
        return game.getGame_id();
    }

    @JsonIgnore
    public void setGame(Game game) {
        this.game = game;
    }


}
