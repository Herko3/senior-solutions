package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public String getLocations(){
        return service.getLocations();
    }
}
