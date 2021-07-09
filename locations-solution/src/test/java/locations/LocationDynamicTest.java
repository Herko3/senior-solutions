package locations;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationDynamicTest {

    @TestFactory
    Stream<DynamicTest> isOnEquator(){
        return Stream.of(new Object[][]{{new Location("Budapest", 0, 10), true}, {new Location("Budapest", 15, 10), false}, {new Location("Budapest", 0, 10), true}})
                .map(pair -> DynamicTest.dynamicTest("Equator test for " + pair[0].toString(),()-> assertEquals(pair[1],((Location) pair[0]).isOnEquator())));
    }
}
