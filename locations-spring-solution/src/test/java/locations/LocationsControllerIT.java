package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationsControllerIT {

    @Autowired
    LocationsController controller;

    @Test
    void testGetLocations(){
        List<LocationDto> result = controller.getLocations(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());

        assertThat(result)
                .extracting(LocationDto::getName)
                .contains("Budapest");
    }
}
