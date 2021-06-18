package locations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationParserTest {

    LocationParser parser = new LocationParser();

    @Test
    void tesParseWithValidText(){

        Location result = parser.parse("Budapest,47.497912,19.040235");

        assertEquals("Budapest",result.getName());
        assertEquals(47.497912,result.getLat(),0.00001);
    }

    @Test
    void testParseWithWrongText(){
        assertThrows(IllegalArgumentException.class, ()->parser.parse("Budapest,47,497912,19.040235"));
    }

    @Test
    void testParseWithWrongNumberText(){
       IllegalStateException ise = assertThrows(IllegalStateException.class, ()->parser.parse("Budapest,Budapest,19.040235"));

       assertEquals("Cannot parse latitude or longitude", ise.getMessage());
    }

}