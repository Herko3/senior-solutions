package activitytracker;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "track_points")
@NamedQuery(name = "getCoordinates", query = "select new activitytracker.Coordinate(t.lat,t.lon) from TrackPoint t where t.activity.startTime > :time order by t.pos")
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate time;
    private double lat;
    private double lon;
    private int pos;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Activity activity;

    public TrackPoint(LocalDate time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }
}
