package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AreaDaoIT {

    ActivityDao activityDao;
    AreaDao areaDao;

    @BeforeEach
    void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);
        areaDao = new AreaDao(factory);
    }

    @Test
    void testSaveArea() {
        Activity activity1 = new Activity(LocalDateTime.of(2021, 1, 2, 10, 50), "Biking in the rain", ActivityType.BIKING);
        Activity activity2 = new Activity(LocalDateTime.of(2021, 2, 2, 10, 50), "Hiking in the rain", ActivityType.HIKING);
        Activity activity3 = new Activity(LocalDateTime.of(2022, 2, 2, 10, 50), "Walking in the rain", ActivityType.HIKING);

        Area area1 = new Area("Area 1");
        Area area2 = new Area("Area 51");
        Area area3 = new Area("Area 3");

        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);
        activityDao.saveActivity(activity3);

        area1.addActivity(activity1);
        area1.addActivity(activity3);

        area2.addActivity(activity2);
        area2.addActivity(activity3);

        area3.addActivity(activity2);

        areaDao.saveArea(area1);
        areaDao.saveArea(area2);
        areaDao.saveArea(area3);

        Area result = areaDao.findAreaByName("Area 51");

        assertEquals(List.of("Hiking in the rain", "Walking in the rain"), result.getActivities().stream().map(Activity::getDesc).toList());
    }

}