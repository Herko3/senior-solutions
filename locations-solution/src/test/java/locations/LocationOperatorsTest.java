package locations;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    LocationOperators operators = new LocationOperators();

    @Test
    void testFilterOnNorth() {
        List<Location> toFilter = List.of(
                new Location("Budapest",10,10),
                new Location("Spárta",-10,10),
                new Location("Debrecen",10,10),
                new Location("London",10,10),
                new Location("Eindhoven",-10,10)
        );

        List<String> result = operators.filterOnNorth(toFilter).stream()
                .map(Location::getName)
                .collect(Collectors.toList());

        assertEquals(List.of("Budapest", "Debrecen", "London"),result);
    }

}