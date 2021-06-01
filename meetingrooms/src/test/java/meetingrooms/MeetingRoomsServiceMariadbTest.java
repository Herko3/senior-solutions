package meetingrooms;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingRoomsServiceMariadbTest {

    private MeetingRoomsService service = new MeetingRoomsService(new MariaDbMeetingRoomsRepository());

    @BeforeEach
    void init(){
        service.deleteAll();

        service.save("Alamo",12,10);
        service.save("Chelsea",11,10);
        service.save("Barcelona",10,10);
        service.save("Dortmund",13,10);
    }

    @Test
    void ABCList(){

        List<String> result = service.meetingRoomsByABC();

        assertEquals("Alamo",result.get(0));
        assertEquals("Barcelona",result.get(1));
        assertEquals("Chelsea",result.get(2));
    }

    @Test
    void reverseList(){

        List<String> result = service.meetingRoomsReversed();

        assertEquals("Dortmund",result.get(0));
        assertEquals("Chelsea",result.get(1));
        assertEquals("Barcelona",result.get(2));
        assertEquals("Alamo",result.get(3));
    }

    @Test
    void everySecond(){
        List<String> result = service.meetingRoomsEverySecond();

        assertEquals(2, result.size());
        assertEquals("Barcelona", result.get(0));
        assertEquals("Dortmund", result.get(1));
    }

    @Test
    void listByArea(){
        List<MeetingRoom> result = service.meetingRoomsByArea();

        assertEquals(130,result.get(0).getArea());
        assertEquals("Alamo",result.get(1).getName());
        assertEquals(11,result.get(2).getWidth());
    }

    @Test
    void exactSearch(){
        assertEquals(110,service.exactSearch("Chelsea").getArea());
    }

    @Test
    void partSearch(){
        List<MeetingRoom> result = service.partSearch("O");

        assertEquals(3,result.size());
        assertEquals("Barcelona",result.get(1).getName());
    }

    @Test
    void searchByArea(){
        List<MeetingRoom> result = service.searchByArea(110);

        assertEquals(2,result.size());
        assertEquals("Dortmund",result.get(1).getName());
        assertEquals(120,result.get(0).getArea());
    }
}
