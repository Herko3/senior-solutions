package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationServiceFileTest {

    @TempDir
    Path temp;

    @Test
    void testWriteLocations() throws IOException {
        List<Location> locations = List.of(
                new Location("Budapest", 47.497912, 19.040235),
                new Location("London", 51.60411, -0.36665),
                new Location("Reykjavik", 64.1424, -21.93908)
        );
        Path file = temp.resolve("locations.csv");
        new LocationService().writeLocations(file,locations);

        List<String> actual = Files.readAllLines(file);

        assertEquals("Budapest,47.497912,19.040235", actual.get(0));
        assertEquals("Reykjavik,64.1424,-21.93908", actual.get(2));
    }
}
