package bicyclesharing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BikeServiceTest {

    BikeService service;

    @BeforeEach
    void init(){
        service = new BikeService(new ModelMapper());
    }

    @Test
    void getAllRides() {
        List<BikeDto> rides = service.getAllRides();

        assertThat(rides)
                .extracting(BikeDto::getBikeId)
                .contains("FH675");
    }

    @Test
    void cacheTest(){
        assertTrue(service.getRides().isEmpty());
    }

    @Test
    void getIds() {
        List<String> result = service.getIds();

        assertThat(result)
                .hasSize(5)
                .contains("US336");
    }
}