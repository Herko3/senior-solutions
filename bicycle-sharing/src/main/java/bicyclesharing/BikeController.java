package bicyclesharing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BikeController {

    private BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/history")
    public List<BikeDto> getAllRides(){
        return bikeService.getAllRides();
    }

    @GetMapping("/users")
    public List<String> getRiderIds(){
        return bikeService.getIds();
    }
}
