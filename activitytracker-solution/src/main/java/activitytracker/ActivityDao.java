package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityDao {

    private final EntityManagerFactory entityManagerFactory;

    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveActivity(Activity activity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);

        em.getTransaction().commit();
        em.close();
    }

    public Activity findActivityById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.find(Activity.class, id);
        em.close();
        return activity;
    }

    public List<Activity> listActivities() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Activity> activities = em.createQuery("select a from Activity a", Activity.class).getResultList();
        em.close();
        return activities;
    }

    public void updateActivity(long id, String desc) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.find(Activity.class, id);
        em.getTransaction().begin();
        activity.setDesc(desc);
        em.getTransaction().commit();

        em.close();
    }

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a join fetch a.labels where a.id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }

    public void addTrackPoint(long id, TrackPoint trackPoint) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Activity activity = em.getReference(Activity.class, id);
        trackPoint.setActivity(activity);
        em.persist(trackPoint);

        em.getTransaction().commit();
        em.close();
    }

    public Activity findActivityByIdWithTrackPoints(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Activity activity = em.createQuery("select distinct a from Activity a left join fetch a.trackPoints where a.id = :id",Activity.class)
                .setParameter("id",id)
                .getSingleResult();
        em.close();
        return activity;
    }

    public void deleteActivityById(long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class,id);
        em.remove(activity);
        em.getTransaction().commit();
        em.close();
    }

    public Activity findActivityByDescription(String description){
        EntityManager em = entityManagerFactory.createEntityManager();

        CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Activity> c = cb.createQuery(Activity.class);
        Root<Activity> act = c.from(Activity.class);

        c.select(act).where(cb.equal(act.get("desc"), description));
        Activity activity = em.createQuery(c).getSingleResult();
        em.close();

        return activity;
    }

    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int max){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Coordinate> coordinates = em.createNamedQuery("getCoordinates",Coordinate.class)
                .setParameter("time", afterThis)
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();

        em.close();

        return coordinates;
    }

    public List<Object[]> findTrackPointCountByActivity(){
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Object[]> data = em.createQuery("select a.desc, SIZE(a.trackPoints) from Activity a order by a.desc")
                .getResultList();
        em.close();
        return data;
    }
}
