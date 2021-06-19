package framework;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocationsController {


    @GetMapping("/")
    @ResponseBody
    public String getLocations(){
        return new Location(1,"Budapest",15,15).toString();
    }

}
