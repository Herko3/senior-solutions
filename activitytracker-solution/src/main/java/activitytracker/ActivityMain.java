package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityMain {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Activity activity1 = new Activity(LocalDateTime.now(),"Biking in somewhere",ActivityType.BIKING);
        Activity activity2 = new Activity(LocalDateTime.now(),"Bask in somewhere",ActivityType.BASKETBALL);
        Activity activity3 = new Activity(LocalDateTime.now(),"Hiking in somewhere",ActivityType.HIKING);

        saveActivity(em,activity1);
        saveActivity(em,activity2);
        saveActivity(em,activity3);

        em.getTransaction().commit();

        System.out.println(activity2.getId());

        long id = activity3.getId();

        activity3 = em.find(Activity.class,id);
        System.out.println(activity3);

        em.getTransaction().begin();
        activity3 = em.find(Activity.class,id);
        activity3.setDesc("Hiking in the woods");
        em.getTransaction().commit();

        List<Activity> activities = em.createQuery("select a from Activity a", Activity.class).getResultList();

        System.out.println(activities);

        em.getTransaction().begin();
        activity3 = em.find(Activity.class,id);
        em.remove(activity3);
        em.getTransaction().commit();

        activities = em.createQuery("select a from Activity a", Activity.class).getResultList();
        System.out.println(activities);

        em.close();
        factory.close();
    }

    private static void  saveActivity(EntityManager em, Activity activity){
        em.persist(activity);
    }
}
