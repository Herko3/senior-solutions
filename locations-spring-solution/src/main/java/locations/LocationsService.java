package locations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LocationsService {

    private ModelMapper mapper;
    private AtomicLong idGenerator = new AtomicLong();


    private List<Location> locations = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(idGenerator.incrementAndGet(), "Budapest", 47.1578, 19.4252),
            new Location(idGenerator.incrementAndGet(), "Eindhoven", 51.4537, 5.4702),
            new Location(idGenerator.incrementAndGet(), "London", 51.5368, -0.1308)
    )));

    public LocationsService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<LocationDto> getLocations(Optional<String> prefix, Optional<Double> minLat,Optional<Double> minLon, Optional<Double> maxLat,Optional<Double> maxLon ) {
        return locations.stream()
                .filter(l->prefix.isEmpty() || l.getName().toLowerCase().contains(prefix.get().toLowerCase()))
                .filter(l->minLat.isEmpty() || l.getLat() > minLat.get())
                .filter(l->minLon.isEmpty() || l.getLon() > minLon.get())
                .filter(l->maxLat.isEmpty() || l.getLat() < maxLat.get())
                .filter(l->maxLon.isEmpty() || l.getLon() < maxLon.get())
                .map(l->mapper.map(l,LocationDto.class))
                .toList();
    }

    public LocationDto findLocationById(long id) {
        return mapper.map(locationById(id), LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGenerator.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
        locations.add(location);
        return mapper.map(location,LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = locationById(id);

        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());

        return mapper.map(location,LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = locationById(id);

        locations.remove(location);
    }

    public void deleteAllLocations(){
        idGenerator = new AtomicLong();
        locations.clear();
    }

    private Location locationById(long id){
        return locations.stream()
                .filter(l->l.getId()==id)
                .findFirst()
                .orElseThrow(()->new LocationNotFound(id));
    }
}
