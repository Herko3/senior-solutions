package meetingrooms;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository {

    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void save(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    @Override
    public List<String> meetingRoomsByABC() {
        return meetingRooms.stream()
                .map(MeetingRoom::getName)
                .sorted(Collator.getInstance(new Locale("hu", "HU")))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> meetingRoomsReversed() {
        return meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getName, Collator.getInstance(new Locale("hu", "HU"))).reversed())
                .map(MeetingRoom::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> meetingRoomsEverySecond() {
        return IntStream
                .range(0, meetingRooms.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> {
                    meetingRooms.sort(Comparator.comparing(MeetingRoom::getName, Collator.getInstance(new Locale("hu","HU"))));
                    return meetingRooms.get(i);
                })
                .map(MeetingRoom::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> meetingRoomsByArea() {
        return meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getArea).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public MeetingRoom exactSearch(String name) {
        return meetingRooms.stream()
                .filter(m -> m.getName().equals(name))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("No meetingroom with name: " + name));
    }

    @Override
    public List<MeetingRoom> partSearch(String part) {
        return meetingRooms.stream()
                .filter(m -> m.getName().toLowerCase().contains(part.toLowerCase()))
                .sorted(Comparator.comparing(MeetingRoom::getName, Collator.getInstance(new Locale("hu", "HU"))))
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> searchByArea(int area) {
        return meetingRooms.stream()
                .filter(m -> m.getArea() > area)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        meetingRooms.clear();
    }
}
