package dk.java.hvz.humansvszombiesbackend.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @Column(length = 30, nullable = false)
    private String first_name;

    @Column(length = 30, nullable = false)
    private String last_name;

    @Column(length = 40, nullable = false, unique = true)
    private String keycloak;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    public String getKeycloak() {
        return keycloak;
    }

    public void setKeycloak(String keycloak) {
        this.keycloak = keycloak;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "user")
    private Set<Player> players;

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
