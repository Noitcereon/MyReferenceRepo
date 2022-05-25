package dk.java.hvz.humansvszombiesbackend.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class SquadCheckin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long squad_checkin_id;

    @Column(nullable = false)
    private Timestamp check_in_time;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "squad_member_id", referencedColumnName = "squad_member_id")
    private SquadMember member;

    public long getSquad_checkin_id() {
        return squad_checkin_id;
    }

    public void setSquad_checkin_id(long squad_checkin_id) {
        this.squad_checkin_id = squad_checkin_id;
    }

    public Timestamp getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(Timestamp check_in_time) {
        this.check_in_time = check_in_time;
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
