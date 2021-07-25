package game.match;

import game.fighter.CreateFighterCommand;
import game.fighter.FighterService;
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
class ContestControllerIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    FighterService service;

    @BeforeEach
    void setUp() {
        service.deleteAll();
        service.saveFighter(new CreateFighterCommand("Jack Doe", 100, 20));
        service.saveFighter(new CreateFighterCommand("John Doe", 120, 20));

        template.delete("/api/matches");
    }

    @Test
    void getMatches() {
        template.postForObject("/api/matches", new CreateMatchCommand(1, 2), MatchDto.class);
        template.postForObject("/api/matches", new CreateMatchCommand(2, 1), MatchDto.class);

        List<MatchDto> result = template.exchange("/api/matches",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MatchDto>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(MatchDto::getResult)
                .contains("Winner is: John Doe");
    }

    @Test
    void findById() {
        template.postForObject("/api/matches", new CreateMatchCommand(1, 2), MatchDto.class);

        MatchDto result = template.getForObject("/api/matches/1",MatchDto.class);

        assertEquals("Jack Doe",result.getFirstContestant());
    }

    @Test
    void startMatch() {
        MatchDto result = template.postForObject("/api/matches", new CreateMatchCommand(1, 2), MatchDto.class);

        assertEquals("John Doe",result.getSecondContestant());
        assertEquals("Winner is: John Doe",result.getResult());
    }
}