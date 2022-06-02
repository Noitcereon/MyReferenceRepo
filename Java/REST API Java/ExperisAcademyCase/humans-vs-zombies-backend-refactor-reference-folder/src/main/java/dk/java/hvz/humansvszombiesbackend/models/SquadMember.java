package dk.java.hvz.humansvszombiesbackend.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SquadMember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long squad_member_id;

    @Column(length = 30, nullable = false)
    private String rank;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "squad_id", referencedColumnName = "squad_id")
    private Squad squad;

    @OneToMany(mappedBy = "member")
    private Set<SquadCheckin> checkins = new HashSet<>();

    public long getSquad_member_id() {
        return squad_member_id;
    }

    public void setSquad_member_id(long squad_member_id) {
        this.squad_member_id = squad_member_id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
