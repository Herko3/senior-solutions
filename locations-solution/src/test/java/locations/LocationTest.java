package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    LocationParser parser;

    Object[][] values = {{new Location("Budapest", 0, 10), true}, {new Location("Budapest", 15, 10), false}, {new Location("Budapest", 0, 10), true}};

    @BeforeEach
    void init() {
        parser = new LocationParser();
    }

    @Test
    void testParse() {
        Location result = parser.parse("Budapest,47.497912,19.040235");

        assertAll(
                () -> assertEquals("Budapest", result.getName()),
                () -> assertEquals(47.497912, result.getLat(), 0.00001),
                () -> assertEquals(19.040235, result.getLon(), 0.00001)
        );


    }

    @Test
    void isNotOnEquatorTest() {
        Location l = parser.parse("Budapest,47.497912,19.040235");

        assertFalse(l.isOnEquator());
    }

    @RepeatedTest(value = 3, name = "Is on Equator {currentRepetition}/{totalRepetitions}")
    void isOnEquatorTest(RepetitionInfo info) {
        assertEquals(values[info.getCurrentRepetition()-1][1],((Location) values[info.getCurrentRepetition() -1][0]).isOnEquator());
    }

    @Test
    void isMeridianPrime() {
        Location l = parser.parse("Budapest,47.497912,0");

        assertTrue(l.isOnPrimeMeridian());
    }

    @Test
    void isNotMeridianPrime() {
        Location l = parser.parse("Budapest,47.497912,19.040235");

        assertFalse(l.isOnPrimeMeridian());
    }

    @Test
    void testDistance() {
        Location location = parser.parse("Budapest,47.508435,19.038555");
        Location other = parser.parse("Debrecen,47.538287,21.632275");

        assertEquals(194.77, location.distanceFrom(other), 0.01);
    }

    @Test
    void anotherObject() {
        Location location = parser.parse("Budapest,47.508435,19.038555");
        Location location2 = parser.parse("Budapest,47.508435,19.038555");

        assertNotEquals(location, location2);
    }

    @Test
    void invalidLatitude() {

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> parser.parse("Budapest,91,19.038555"));

        assertEquals("Invalid longitude or latitude", iae.getMessage());
    }

    @Test
    void invalidLatitudeWithNegative() {

        assertThrows(IllegalArgumentException.class, () -> parser.parse("Budapest,-91,19.038555"));
    }

    @Test
    void invalidLongitude() {

        assertThrows(IllegalArgumentException.class, () -> parser.parse("Budapest,15,1855"));

    }

    @Test
    void invalidLongitudeWithNegative() {

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> parser.parse("Budapest,15,-185"));

        assertEquals("Invalid longitude or latitude", iae.getMessage());
    }

}