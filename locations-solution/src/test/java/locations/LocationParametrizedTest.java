package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationParametrizedTest {

    LocationParser parser;

    @BeforeEach
    void init(){
        parser = new LocationParser();
    }

    @ParameterizedTest(name = "The location {0}, is on meridian: {1}")
    @MethodSource("testMeridian")
    void testIsOnMeridian(String location, boolean expected){

        assertEquals(expected, parser.parse(location).isOnPrimeMeridian());

    }

    static Stream<Arguments> testMeridian() {
        return Stream.of(
                Arguments.arguments("Budapest,0,0", true),
                Arguments.arguments("Budapest,85,0", true),
                Arguments.arguments("Budapest,0,10", false),
                Arguments.arguments("Budapest,0,35", false)
        );
    }

    @ParameterizedTest(name = "Distance between two location : {4}")
    @CsvFileSource(resources = "/locations.csv")
    void testDistance(double lat1, double lon1, double lat2, double lon2, double distance){
        Location l = new Location("Budapest", lat1,lon1);
        Location l2 = new Location("Budapest", lat2,lon2);

        assertEquals(distance, l.distanceFrom(l2), 0.01);
    }

}
