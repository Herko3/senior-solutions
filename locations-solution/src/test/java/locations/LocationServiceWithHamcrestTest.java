package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocationServiceWithHamcrestTest {

    List<Location> locations;

    @BeforeEach
    void init(){
        locations = new LocationService().locationsFromFile(Path.of("src/main/resources/locations.csv"));
    }

    @Test
    void testHamcrest(){

        assertThat(locations, contains(
                hasProperty("name", equalTo("Budapest")),
                hasProperty("lat", closeTo(51.60411,0.01)),
                hasProperty("lon", closeTo(16.39009, 0.01))
                ));

        assertThat(locations, hasItem(
                hasProperty("name", startsWith("Lond"))
        ));
    }
}
