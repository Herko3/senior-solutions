package locations;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    List<Location> locations;

    @BeforeEach
    void init(){
        locations = new LocationService().locationsFromFile(Path.of("src/main/resources/locations.csv"));
    }

    @Test
    void testLocationsFromFile(){


        assertThat(locations)
                .hasSize(3)
                .extracting(Location::getName)
                .contains("Budapest","London");
    }

    @Test
    void filterLocationTest(){
        assertThat(locations)
                .filteredOn(e -> e.getName().contains("L"))
                .extracting(Location::getName,Location::isOnEquator)
                .containsOnly(tuple("London",false));
    }

    @Test
    void testWithCondition(){
        Location location = locations.get(1);

        Condition<Location> zeroCoordinate = new Condition<>(l->l.getLat() == 0 || l.getLon() == 0,"has at least one zero coordinate");

        assertThat(location).has(zeroCoordinate);
    }

}