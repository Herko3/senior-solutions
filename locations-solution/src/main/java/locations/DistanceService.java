package locations;

import java.util.Optional;

public class DistanceService {

    private LocationRepository locationRepository;

    public DistanceService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Double> calculateDistance(String name1, String name2){
        Optional<Location> location1 = locationRepository.findByName(name1);
        Optional<Location> location2 = locationRepository.findByName(name2);

        if(location1.isPresent() && location2.isPresent()){
            return Optional.of(location1.get().distanceFrom(location2.get()));
        }

        return Optional.empty();
    }
}
