package movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerRestTest {

    @Autowired
    TestRestTemplate template;


    @Test
    void testCreateMovie(){
        MovieDto result = template.postForObject("/api/movies", new CreateMovieCommand("Titanic", 120), MovieDto.class);

        assertEquals("Titanic",result.getTitle());

        MovieDto result2 = template.getForObject("/api/movies/1",MovieDto.class);

        assertEquals("Titanic",result.getTitle());
    }
}
