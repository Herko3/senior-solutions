package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class LocationsController {

    private LocationsService service;

    public LocationsController(LocationsService service) {
        this.service = service;
    }

    @GetMapping("/locations")
    public String getLocations(){
        return service.getLocations().stream()
                .map(Location::toString)
                .collect(Collectors.joining("\n"));
    }
}
