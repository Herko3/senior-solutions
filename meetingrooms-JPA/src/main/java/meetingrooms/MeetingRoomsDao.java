package meetingrooms;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class MeetingRoomsDao implements MeetingRoomRepository {

    private final EntityManagerFactory factory;

    @Override
    public MeetingRoom save(String name, int width, int length) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        em.persist(meetingRoom);

        em.getTransaction().commit();
        em.close();
        return meetingRoom;
    }

    @Override
    public List<String> getMeetingRoomsOrderedByName() {
        EntityManager em = factory.createEntityManager();
        List<String> roomNames = em.createQuery("select m.name from MeetingRoom m order by m.name", String.class)
                .getResultList();
        em.close();
        return roomNames;
    }

    @Override
    public List<String> getEverySecondMeetingRoom() {
        EntityManager em = factory.createEntityManager();
        List<String> roomNames = em.createQuery("select m.name from MeetingRoom m order by m.name", String.class)
                .getResultList();
        em.close();

        return IntStream.range(0, roomNames.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(roomNames::get)
                .toList();
    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        EntityManager em = factory.createEntityManager();
        List<MeetingRoom> meetingRooms = em.createQuery("select m from MeetingRoom m order by m.name", MeetingRoom.class)
                .getResultList();
        em.close();
        return meetingRooms;
    }

    @Override
    public List<MeetingRoom> getExactMeetingRoomByName(String name) {
        EntityManager em = factory.createEntityManager();

        List<MeetingRoom> meetingRooms = em.createQuery("select m from MeetingRoom m where m.name = :name order by m.name", MeetingRoom.class)
                .setParameter("name", name)
                .getResultList();
        em.close();

        return meetingRooms;
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix) {
        EntityManager em = factory.createEntityManager();

        List<MeetingRoom> meetingRooms = em.createQuery("select m from MeetingRoom m where m.name like :name order by m.name", MeetingRoom.class)
                .setParameter("name", "%" + nameOrPrefix + "%")
                .getResultList();
        em.close();

        return meetingRooms;
    }

    @Override
    public void deleteAll() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from MeetingRoom").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
