package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationNestedTest {

    LocationParser parser;

    @BeforeEach
    void init(){
        parser = new LocationParser();
    }

    @Nested
    class LocationOnZero{
        Location location;

        @BeforeEach
        void init(){
            location = parser.parse("London,0.0,0.0");
        }

        @Test
        void isOnMeridian(){
            assertTrue(location.isOnPrimeMeridian());
        }

        @Test
        void isOnEquator(){
            assertTrue(location.isOnEquator());
        }
    }

    @Nested
    class LocationWithoutZero{
        Location location;

        @BeforeEach
        void init(){
            location = parser.parse("London,47.497912,19.040235");
        }

        @Test
        void isOnMeridian(){
            assertFalse(location.isOnPrimeMeridian());
        }

        @Test
        void isOnEquator(){
            assertFalse(location.isOnEquator());
        }
    }
}
