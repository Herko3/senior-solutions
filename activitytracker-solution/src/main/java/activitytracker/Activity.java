package activitytracker;

import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {

    @TableGenerator(name = "Act_Gen",
            table = "act_id_gen",
            pkColumnName = "id_gen",
            valueColumnName = "id_val")
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

    public Activity() {
    }

    public Activity(LocalDateTime startTime, String desc, ActivityType type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", desc='" + desc + '\'' +
                ", type=" + type +
                '}';
    }
}
