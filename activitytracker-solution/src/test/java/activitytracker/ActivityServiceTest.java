package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityServiceTest {

    private ActivityDao activityDao;

    @BeforeEach
    void init() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);
    }

    @Test
    void testSaveAndFind() {
        Activity activity = new Activity(LocalDateTime.now(), "Biking in the rain", ActivityType.BIKING);
        activityDao.saveActivity(activity);
        Activity loaded = activityDao.findActivityById(activity.getId());

        assertEquals("Biking in the rain", loaded.getDesc());
    }

    @Test
    void testSaveThenList() {
        Activity activity1 = new Activity(LocalDateTime.now(), "Biking in the rain", ActivityType.BIKING);
        Activity activity2 = new Activity(LocalDateTime.now(), "Hiking in the rain", ActivityType.HIKING);

        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);

        List<Activity> activities = activityDao.listActivities();

        assertThat(activities)
                .hasSize(2)
                .extracting(Activity::getType)
                .contains(ActivityType.BIKING, ActivityType.HIKING);
    }
}
