package cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    CarService service;

    @InjectMocks
    CarController controller;

    @Test
    void getCars() {
        when(service.getCars()).thenReturn(List.of(new Car("Tesla","Model 3",5,CarCondition.PERFECT,null)));

        List<Car> result = controller.getCars();

        assertThat(result)
                .extracting(Car::getType)
                .containsOnly("Model 3");

        verify(service).getCars();

    }

    @Test
    void getBrands() {
        when(service.getBrands()).thenReturn(List.of("Tesla","Opel"));

        List<String> result = controller.getBrands();

        assertThat(result)
                .hasSize(2)
                .contains("Tesla");

    }
}