package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationsControllerIT {

    @Autowired
    LocationsController controller;

    @Test
    void testGetLocations(){
        String message = controller.getLocations();

        assertThat(message).startsWith("1 - Budapest");
    }
}
