package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations;

    public LocationsService() {
        locations = List.of(new Location(1,"Budapest",47.1578,19.4252),
                new Location(2,"Debrecen",47.1578,19.4252),
                new Location(3,"London",47.1578,19.4252));
    }

    public List<Location> getLocations() {
        return new ArrayList<>(locations);
    }
}
