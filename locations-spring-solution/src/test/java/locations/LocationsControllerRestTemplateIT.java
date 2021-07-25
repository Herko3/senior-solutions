package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from locations")
public class LocationsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void testGetLocations() {

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
                .containsExactly("Eindhoven", "London");
    }

    @Test
    void testFindLocationById() {

        LocationDto post = template.postForObject("/api/locations", new CreateLocationCommand("London", 51.5368, -0.1308), LocationDto.class);

        LocationDto result = template.getForObject("/api/locations/" + post.getId(), LocationDto.class);

        assertEquals(51.5368, result.getLat(), 0.0001);

    }

    @Test
    void testUpdateLocation() {

        LocationDto post = template.postForObject("/api/locations", new CreateLocationCommand("London", 51.5368, -0.1308), LocationDto.class);

        template.put("/api/locations/" + post.getId(), new UpdateLocationCommand("Budapest", 70, 150));
        LocationDto result = template.getForObject("/api/locations/" + post.getId(), LocationDto.class);

        assertEquals(150, result.getLon());

    }

    @Test
    void testDelete() {
        LocationDto toDelete = template.postForObject("/api/locations", new CreateLocationCommand("Eindhoven", 51.4537, 5.4702), LocationDto.class);
        template.postForObject("/api/locations", new CreateLocationCommand("London", 51.5368, -0.1308), LocationDto.class);

        List<LocationDto> locations = template.exchange("/api/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {
                })
                .getBody();

        assertThat(locations)
                .hasSize(2)
                .extracting(LocationDto::getName)
                .containsExactly("Eindhoven", "London");

        template.delete("/api/locations/" + toDelete.getId());

        List<LocationDto> afterDelete = template.exchange("/api/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {
                })
                .getBody();

        assertThat(afterDelete)
                .hasSize(1)
                .extracting(LocationDto::getName)
                .containsOnly("London");
    }

    @Test
    void testValidationWithWrongLat() {

        ResponseEntity<Problem> problem =
                template.postForEntity("/api/locations", new UpdateLocationCommand("Budapest", 150, 150), Problem.class);

        assertEquals(HttpStatus.BAD_REQUEST, problem.getStatusCode());
    }

    @Test
    void testValidationWithWrongLon() {

        ResponseEntity<Problem> problem =
                template.postForEntity("/api/locations", new UpdateLocationCommand("Budapest", 70, 200), Problem.class);

        assertEquals(HttpStatus.BAD_REQUEST, problem.getStatusCode());
    }

    @Test
    void testValidationWithWrongName() {

        ResponseEntity<Problem> problem =
                template.postForEntity("/api/locations", new UpdateLocationCommand("", 70, 150), Problem.class);

        assertEquals(HttpStatus.BAD_REQUEST, problem.getStatusCode());
    }

    @Test
    void test() {
        ResponseEntity<Problem> problem = template.getForEntity("/api/locations/15", Problem.class);

        assertEquals(HttpStatus.NOT_FOUND, problem.getStatusCode());
    }
}
