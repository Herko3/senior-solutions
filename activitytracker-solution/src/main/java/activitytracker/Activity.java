package activitytracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {

    @TableGenerator(name = "Act_Gen",
            table = "act_id_gen",
            pkColumnName = "id_gen",
            valueColumnName = "id_val",
            allocationSize = 100)
    @Id
    @GeneratedValue(generator = "Act_Gen")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "activity_desc", length = 200, nullable = false)
    private String desc;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", length = 20, nullable = false)
    private ActivityType type;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "act_id"))
    @Column(name = "label")
    private List<String> labels;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, mappedBy = "activity")
//    @OrderBy("time")
    @OrderColumn(name = "pos")
    private List<TrackPoint> trackPoints;

    @PrePersist
    public void persistTime() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updateTime() {
        updatedAt = LocalDateTime.now();
    }

    public Activity(LocalDateTime startTime, String desc, ActivityType type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }

    public void addTrackPoint(TrackPoint trackPoint){
        if(trackPoints == null){
            trackPoints = new ArrayList<>();
        }
        trackPoints.add(trackPoint);
        trackPoint.setActivity(this);
    }
}
