package movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovieControllerIT {

    @Autowired
    MovieController controller;

    @Test
    void createMovie(){
        MovieDto result = controller.createMovie(new CreateMovieCommand("Titanic",120));

        assertEquals("Titanic",result.getTitle());
    }
}
