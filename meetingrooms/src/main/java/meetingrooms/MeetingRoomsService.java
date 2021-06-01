package meetingrooms;

import java.util.List;

public class MeetingRoomsService {

    private MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void save(String name, int width, int length) {
        meetingRoomsRepository.save(name, width, length);
    }

    public List<String> meetingRoomsByABC() {
        return meetingRoomsRepository.meetingRoomsByABC();
    }

    public List<String> meetingRoomsReversed() {
        return meetingRoomsRepository.meetingRoomsReversed();
    }

    public List<String> meetingRoomsEverySecond() {
        return meetingRoomsRepository.meetingRoomsEverySecond();
    }

    public List<MeetingRoom> meetingRoomsByArea() {
        return meetingRoomsRepository.meetingRoomsByArea();
    }

    public MeetingRoom exactSearch(String name) {
        return meetingRoomsRepository.exactSearch(name);
    }

    public List<MeetingRoom> partSearch(String part) {
        return meetingRoomsRepository.partSearch(part);
    }

    public List<MeetingRoom> searchByArea(int area) {
        return meetingRoomsRepository.searchByArea(area);
    }

    public void deleteAll(){
        meetingRoomsRepository.deleteAll();
    }
}
