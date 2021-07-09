package locations;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationLegacyTest {

    public LocationParser parser = new LocationParser();

    @Test
    public void testDistance(){
        Location location = parser.parse("Budapest,47.508435,19.038555");
        Location other = parser.parse("Debrecen,47.538287,21.632275");

        assertEquals(194.77, location.distanceFrom(other), 0.01);
    }
}
