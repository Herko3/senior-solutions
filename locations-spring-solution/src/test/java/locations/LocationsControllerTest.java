package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService service;

    @InjectMocks
    LocationsController controller;

    @Test
    void getLocations() {
        when(service.getLocations(any(),any(),any(),any(),any())).thenReturn(List.of(new LocationDto(1,"Budapest", 47.157,19.154)));

        List<LocationDto> result = controller.getLocations(any(),any(),any(),any(),any());
        assertThat(result)
                .extracting(LocationDto::getName)
                .contains("Budapest");

        verify(service).getLocations(any(),any(),any(),any(),any());
    }
}