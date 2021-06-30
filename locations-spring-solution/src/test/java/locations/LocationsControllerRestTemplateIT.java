package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    LocationsService service;

    @Test
    void testGetLocations() {
        service.deleteAllLocations();

        LocationDto locationDto =
                template.postForObject("/api/locations", new CreateLocationCommand("Eindhoven", 51.4537, 5.4702), LocationDto.class);

        assertEquals("Eindhoven", locationDto.getName());

        template.postForObject("/api/locations", new CreateLocationCommand("London", 51.5368, -0.1308), LocationDto.class);

        List<LocationDto> locations = template.exchange("/api/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {
                })
                .getBody();

        assertThat(locations)
                .extracting(LocationDto::getName)
                .containsExactly("Eindhoven","London");
    }
}
