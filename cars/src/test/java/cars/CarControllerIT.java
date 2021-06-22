package cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CarControllerIT {

    @Autowired
    CarController controller;

    @Test
    void testGetCars(){
        List<Car> result = controller.getCars();

        assertThat(result)
                .extracting(Car::getType)
                .contains("Astra","Model 3");

    }

    @Test
    void testGetBrands(){
        List<String> result = controller.getBrands();

        assertThat(result)
                .hasSize(4)
                .contains("Tesla");
    }
}
