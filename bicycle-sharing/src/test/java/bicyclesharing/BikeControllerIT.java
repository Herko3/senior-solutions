package bicyclesharing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BikeControllerIT {

    @Autowired
    BikeController controller;

    @Test
    void getAllRides(){
        List<BikeDto> result = controller.getAllRides();

        assertThat(result)
                .extracting(BikeDto::getLastRiderId)
                .contains("US336");
    }

    @Test
    void getRiderId(){

        List<String> result = controller.getRiderIds();

        assertThat(result)
                .contains("US336");
    }
}
