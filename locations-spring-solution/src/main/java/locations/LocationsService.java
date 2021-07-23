package locations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LocationsService {

    private ModelMapper mapper;

    private LocationsRepository repository;

    public LocationsService(ModelMapper mapper, LocationsRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<LocationDto> getLocations(Optional<String> prefix, Optional<Double> minLat, Optional<Double> minLon, Optional<Double> maxLat, Optional<Double> maxLon) {
        return repository.findAll().stream()
                .filter(l -> prefix.isEmpty() || l.getName().toLowerCase().contains(prefix.get().toLowerCase()))
                .filter(l -> minLat.isEmpty() || l.getLat() > minLat.get())
                .filter(l -> minLon.isEmpty() || l.getLon() > minLon.get())
                .filter(l -> maxLat.isEmpty() || l.getLat() < maxLat.get())
                .filter(l -> maxLon.isEmpty() || l.getLon() < maxLon.get())
                .map(l -> mapper.map(l, LocationDto.class))
                .toList();
    }

    public LocationDto findLocationById(long id) {
        return mapper.map(repository.findById(id).orElseThrow(() -> new LocationNotFound(id)), LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(command.getName(), command.getLat(), command.getLon());
        repository.save(location);
        return mapper.map(location, LocationDto.class);
    }

    @Transactional
    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = repository.findById(id).orElseThrow(()->new LocationNotFound(id));

        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());

        return mapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        repository.deleteById(id);
    }

    public void deleteAllLocations() {
        repository.deleteAll();
    }

}
