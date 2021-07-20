package meetingrooms;

import java.util.List;

public interface MeetingRoomRepository {

    MeetingRoom save(String name, int width, int length);

    List<String> getMeetingRoomsOrderedByName();
}
