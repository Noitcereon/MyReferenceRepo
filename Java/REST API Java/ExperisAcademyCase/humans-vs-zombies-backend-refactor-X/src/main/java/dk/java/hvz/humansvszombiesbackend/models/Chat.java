package dk.java.hvz.humansvszombiesbackend.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long message_id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(length = 1, nullable = false)
    private String is_human_global;

    @Column(length = 1, nullable = false)
    private String is_zombie_global;

    @Column(nullable = false)
    private Timestamp chat_time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    private Game game;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIs_human_global() {
        return is_human_global;
    }

    public void setIs_human_global(String is_human_global) {
        this.is_human_global = is_human_global;
    }

    public String getIs_zombie_global() {
        return is_zombie_global;
    }

    public void setIs_zombie_global(String is_zombie_global) {
        this.is_zombie_global = is_zombie_global;
    }

    public Timestamp getChat_time() {
        return chat_time;
    }

    public void setChat_time(Timestamp chat_time) {
        this.chat_time = chat_time;
    }
}
