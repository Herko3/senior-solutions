package cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    CarService service;

    @BeforeEach
    void init(){
        service = new CarService();
    }


    @Test
    void testGetCars(){
        List<Car> cars = service.getCars();

        assertThat(cars)
                .extracting(Car::getAge)
                .contains(15,10,5);
    }

    @Test
    void getBrands(){
        List<String> brands = service.getBrands();

        assertThat(brands)
                .hasSize(4)
                .contains("Tesla");
    }

}