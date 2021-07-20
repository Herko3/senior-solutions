package meetingrooms;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@AllArgsConstructor
public class MeetingRoomsDao implements MeetingRoomRepository {

    private EntityManagerFactory factory;

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
        List<MeetingRoom> meetingRooms = em.createQuery("select m from MeetingRoom m order by m.name", MeetingRoom.class)
                .getResultList();
        em.close();
        return meetingRooms.stream()
                .map(MeetingRoom::getName)
                .toList();
    }
}
