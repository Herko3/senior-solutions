package mathematician;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathematiciansIT {

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void init() {
        template.delete("/api/mathematicians");

        template.postForObject("/api/mathematicians", new CreateMathematicianCommand("John Doe", List.of("topic1"), LocalDate.of(2015, 10, 10), 3), MathematicianDTO.class);
        template.postForObject("/api/mathematicians", new CreateMathematicianCommand("Jack Doe", List.of("topic2"), LocalDate.of(2000, 10, 10), 97), MathematicianDTO.class);
    }

    @Test
    void testPost() {
        template.postForObject("/api/mathematicians", new CreateMathematicianCommand("Jane Doe", List.of("topic3"), LocalDate.of(1995, 10, 10), 7), MathematicianDTO.class);

        List<MathematicianDTO> result = template.exchange("/api/mathematicians",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MathematicianDTO>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(3)
                .extracting(MathematicianDTO::getName)
                .contains("Jane Doe");
    }

    @Test
    void invalidName() {
        Problem problem = template.postForObject("/api/mathematicians", new CreateMathematicianCommand(
                "", List.of("topic3"), LocalDate.of(1995, 10, 10), 7), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());

        List<MathematicianDTO> result = template.exchange("/api/mathematicians",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MathematicianDTO>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(MathematicianDTO::getName)
                .containsExactly("John Doe","Jack Doe");
    }

    @Test
    void invalidTopic() {
        Problem problem = template.postForObject("/api/mathematicians", new CreateMathematicianCommand(
                "Jane Doe", null, LocalDate.of(1995, 10, 10), 7), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());

        List<MathematicianDTO> result = template.exchange("/api/mathematicians",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MathematicianDTO>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(MathematicianDTO::getName)
                .containsExactly("John Doe","Jack Doe");
    }

    @Test
    void invalidBirthDate() {
        Problem problem = template.postForObject("/api/mathematicians", new CreateMathematicianCommand(
                "Jane Doe", List.of("topic3"), LocalDate.of(2022, 10, 10), 7), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());

        List<MathematicianDTO> result = template.exchange("/api/mathematicians",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MathematicianDTO>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(MathematicianDTO::getName)
                .containsExactly("John Doe","Jack Doe");
    }

    @Test
    void invalidPrime() {
        Problem problem = template.postForObject("/api/mathematicians", new CreateMathematicianCommand(
                "Jane Doe", List.of("topic3"), LocalDate.of(1995, 10, 10), 15), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());

        List<MathematicianDTO> result = template.exchange("/api/mathematicians",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MathematicianDTO>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(MathematicianDTO::getName)
                .containsExactly("John Doe","Jack Doe");
    }

}
