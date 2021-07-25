package game.fighter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FighterControllerIT {

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void setUp() {
        template.delete("/api/fighters");

        template.postForObject("/api/fighters", new CreateFighterCommand("Jack Doe", 100, 20), FighterDto.class);
        template.postForObject("/api/fighters", new CreateFighterCommand("John Doe", 120, 20), FighterDto.class);

    }

    @Test
    void findById() {
        FighterDto result = template.getForObject("/api/fighters/1", FighterDto.class);

        assertEquals("Jack Doe", result.getName());
        assertEquals(20, result.getDamage());
    }

    @Test
    void getFighters() {

        List<FighterDto> result = template.exchange("/api/fighters",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FighterDto>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(FighterDto::getName)
                .contains("Jack Doe", "John Doe");

    }

    @Test
    void saveFighter() {
        FighterDto result = template.postForObject("/api/fighters", new CreateFighterCommand("Jack Doe", 100, 20), FighterDto.class);

        assertEquals("Jack Doe", result.getName());
        assertEquals(100, result.getHealthPoint());
    }

    @Test
    void updateFighter() {
        template.put("/api/fighters/2",new UpdateFighterCommand("Jane Doe",150,10));
        FighterDto result = template.getForObject("/api/fighters/2", FighterDto.class);

        assertEquals("Jane Doe",result.getName());
        assertEquals(150,result.getHealthPoint());
    }

    @Test
    void deleteById() {
        List<FighterDto> preDelete = template.exchange("/api/fighters",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FighterDto>>() {
                })
                .getBody();

        assertThat(preDelete)
                .hasSize(2)
                .extracting(FighterDto::getName)
                .contains("Jack Doe", "John Doe");

        template.delete("/api/fighters/2");

        List<FighterDto> result = template.exchange("/api/fighters",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FighterDto>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(1)
                .extracting(FighterDto::getName)
                .containsOnly("Jack Doe");
    }
}