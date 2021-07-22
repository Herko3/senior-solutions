package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityServiceIT {

    ActivityDao activityDao;

    @BeforeEach
    void init() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);

        Activity activity1 = new Activity(LocalDateTime.now(), "Biking in the rain", ActivityType.BIKING);
        Activity activity2 = new Activity(LocalDateTime.now(), "Hiking in the rain", ActivityType.HIKING);

        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);
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
        List<Activity> activities = activityDao.listActivities();

        assertThat(activities)
                .hasSize(2)
                .extracting(Activity::getType)
                .contains(ActivityType.BIKING, ActivityType.HIKING);
    }

    @Test
    void testUpdateActivity() {
        Activity activity = new Activity(LocalDateTime.now(), "Biking in the rain", ActivityType.BIKING);
        activityDao.saveActivity(activity);

        activityDao.updateActivity(activity.getId(), "Change desc");

        Activity loaded = activityDao.findActivityById(activity.getId());

        assertEquals("Change desc", loaded.getDesc());
    }

    @Test
    void testLabels() {
        Activity activity = new Activity(LocalDateTime.now(), "Biking in the rain", ActivityType.BIKING);
        activity.setLabels(List.of("Bike", "Rain"));
        activityDao.saveActivity(activity);

        Activity loaded = activityDao.findActivityByIdWithLabels(activity.getId());

        assertEquals(List.of("Bike", "Rain"), loaded.getLabels());
    }

    @Test
    void testAddTrackPoint() {
        Activity activity = new Activity(LocalDateTime.now(), "Biking in the rain", ActivityType.BIKING);
        activityDao.saveActivity(activity);
        activityDao.addTrackPoint(activity.getId(), new TrackPoint(LocalDate.of(2021, 1, 2), 15, 15));

        Activity another = activityDao.findActivityByIdWithTrackPoints(activity.getId());

        assertEquals(1, another.getTrackPoints().size());
    }

    @Test
    void testTrackPoint() {
        TrackPoint tp = new TrackPoint(LocalDate.of(2021, 2, 2), 15, 15);
        TrackPoint tp2 = new TrackPoint(LocalDate.of(2021, 1, 2), 10, 15);

        Activity activity = new Activity(LocalDateTime.now(), "Biking in the rain", ActivityType.BIKING);
        activity.addTrackPoint(tp);
        activity.addTrackPoint(tp2);

        activityDao.saveActivity(activity);

        Activity another = activityDao.findActivityByIdWithTrackPoints(activity.getId());

//        assertEquals(10,another.getTrackPoints().get(0).getLat());
        assertEquals(15, another.getTrackPoints().get(0).getLat());
    }

    @Test
    void testRemove(){
        TrackPoint tp = new TrackPoint(LocalDate.of(2021, 2, 2), 15, 15);
        TrackPoint tp2 = new TrackPoint(LocalDate.of(2021, 1, 2), 10, 15);

        Activity activity = new Activity(LocalDateTime.now(), "Biking in the rain", ActivityType.BIKING);
        activity.addTrackPoint(tp);
        activity.addTrackPoint(tp2);

        activityDao.saveActivity(activity);

        activityDao.deleteActivityById(activity.getId());
    }
}
