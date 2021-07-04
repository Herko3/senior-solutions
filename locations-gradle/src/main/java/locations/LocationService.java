package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LocationService {

    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(1, "Budapest", 47.1578, 19.4252),
            new Location(2, "Eindhoven", 51.4537, 5.4702),
            new Location(3, "London", 51.5368, -0.1308)
    )));

    public String getLocations() {
        return locations.toString();
    }
}
