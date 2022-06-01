package dk.java.hvz.humansvszombiesbackend.models;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long mission_id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 1, nullable = false)
    private String is_human_visible;

    @Column(length = 1, nullable = false)
    private String is_zombie_visible;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Timestamp start_time;

    @Column private Time end_time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private Game game;

    public long getMission_id() {
        return mission_id;
    }

    public void setMission_id(long mission_id) {
        this.mission_id = mission_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_human_visible() {
        return is_human_visible;
    }

    public void setIs_human_visible(String is_human_visible) {
        this.is_human_visible = is_human_visible;
    }

    public String getIs_zombie_visible() {
        return is_zombie_visible;
    }

    public void setIs_zombie_visible(String is_zombie_visible) {
        this.is_zombie_visible = is_zombie_visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
}
