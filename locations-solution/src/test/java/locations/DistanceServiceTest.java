package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DistanceServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    DistanceService service;

    @Test
    void testDistance() throws NoSuchElementException {
        when(locationRepository.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest",47.508435,19.038555)));
        when(locationRepository.findByName("Debrecen")).thenReturn(Optional.of(new Location("Debrecen",47.538287,21.632275)));

        assertEquals(194.77,service.calculateDistance("Budapest","Debrecen").get(),0.01);

        verify(locationRepository).findByName(argThat(e->e.equals("Debrecen")));
    }

    @Test
    void tesDistanceWithEmpty(){
        when(locationRepository.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest",47.508435,19.038555)));
        when(locationRepository.findByName("Debrecen")).thenReturn(Optional.empty());

        assertEquals(Optional.empty(),service.calculateDistance("Budapest","Debrecen"));

        verify(locationRepository).findByName(argThat(e->e.equals("Budapest")));
    }

}