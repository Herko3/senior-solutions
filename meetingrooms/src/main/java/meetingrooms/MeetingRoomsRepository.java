package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void save(String name, int width, int length);

    List<String> meetingRoomsByABC();

    List<String> meetingRoomsReversed();

    List<String> meetingRoomsEverySecond();

    List<MeetingRoom> meetingRoomsByArea();

    MeetingRoom exactSearch(String name);

    List<MeetingRoom> partSearch(String part);

    List<MeetingRoom> searchByArea(int area);

    void deleteAll();

}
