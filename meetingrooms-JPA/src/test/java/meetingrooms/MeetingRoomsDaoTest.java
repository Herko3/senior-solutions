package meetingrooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsDaoTest {

    MeetingRoomsDao dao;

    @BeforeEach
    void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        dao = new MeetingRoomsDao(factory);

        dao.save("C room",10,10);
        dao.save("B room",10,10);

    }

    @Test
    void getMeetingRoomsOrderedByName() {
        List<String> result = dao.getMeetingRoomsOrderedByName();

        assertEquals(List.of("B room", "C room"), result);
    }
}