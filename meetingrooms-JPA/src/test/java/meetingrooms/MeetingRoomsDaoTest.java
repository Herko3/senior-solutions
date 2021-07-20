package meetingrooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void testDeleteAll(){
        dao.deleteAll();
        List<MeetingRoom> meetingRooms = dao.getMeetingRooms();

        assertEquals(Collections.EMPTY_LIST,meetingRooms);
    }

    @Test
    void getMeetingRooms(){
        dao.save("D room", 12,12);

        List<MeetingRoom> result = dao.getMeetingRooms();

        assertThat(result)
                .hasSize(3)
                .extracting(MeetingRoom::getName)
                .contains("D room", "C room");
    }

    @Test
    void getByPrefix(){
        dao.save("Room #2",10,10);
        dao.save("Room #3",10,10);
        dao.save("Room #1",10,10);
        dao.save("Room #4",10,10);

        List<MeetingRoom> result = dao.getMeetingRoomsByPrefix("#");

        assertThat(result)
                .hasSize(4)
                .extracting(MeetingRoom::getName)
                .contains("Room #3");
    }

    @Test
    void testExactSearch(){
        List<MeetingRoom> result = dao.getExactMeetingRoomByName("C room");

        assertThat(result)
                .hasSize(1)
                .extracting(MeetingRoom::getLength)
                .containsOnly(10);
    }

    @Test
    void testExactWithNoResult(){
        List<MeetingRoom> result = dao.getExactMeetingRoomByName("C");

        assertThat(result)
                .hasSize(0);
    }

    @Test
    void testEverySecond(){
        List<String> result = dao.getEverySecondMeetingRoom();

        assertThat(result)
                .hasSize(1)
                .containsOnly("C room");
    }
}