package bicyclesharing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeControllerTest {

    @Mock
    BikeService service;

    @InjectMocks
    BikeController controller;

    @Test
    void getAllRides() {
        when(service.getAllRides()).thenReturn(List.of(new BikeDto("1","Biker8", LocalDateTime.of(2021,6,20,10,1,1),0.5)));

        List<BikeDto> result = controller.getAllRides();

        assertThat(result)
                .extracting(BikeDto::getBikeId)
                .containsOnly("1");

        verify(service).getAllRides();
    }

    @Test
    void getRiderIds() {
        when(service.getIds()).thenReturn(List.of("Biker8"));

        List<String> result = controller.getRiderIds();

        assertThat(result)
                .containsOnly("Biker8");

        verify(service).getIds();
    }
}