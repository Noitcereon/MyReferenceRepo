package dk.java.hvz.humansvszombiesbackend.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long squad_id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 1, nullable = false)
    private String is_human;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private Game game;

    @OneToMany(mappedBy = "squad")
    private Set<SquadMember> members = new HashSet<>();

    public long getSquad_id() {
        return squad_id;
    }

    public void setSquad_id(long squad_id) {
        this.squad_id = squad_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_human() {
        return is_human;
    }

    public void setIs_human(String is_human) {
        this.is_human = is_human;
    }
}
