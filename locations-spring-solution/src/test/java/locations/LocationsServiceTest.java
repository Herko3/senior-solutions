package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    @Test
    void testGetLocations(){
        List<LocationDto> locations = new LocationsService(new ModelMapper()).getLocations(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());
        assertThat(locations)
                .extracting(LocationDto::getName)
                .contains("Budapest","Eindhoven");
    }

}