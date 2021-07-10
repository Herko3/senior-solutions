package movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    MovieService service;

    @BeforeEach
    void setUp() {
        service = new MovieService();

        service.saveMovie(new Movie("Titanic",120, LocalDate.of(2021,10,17)));
        service.saveMovie(new Movie("Random",85, LocalDate.of(2021,10,16)));
        service.saveMovie(new Movie("Fűrész",210, LocalDate.of(2021,10,15)));
    }

    @Test
    void findLatestMovie() {
        Movie result = service.findLatestMovie();

        assertEquals("Titanic", result.getName());
    }

    @Test
    void findMovieByNamePart() {
        Movie result = service.findMovieByNamePart("rész");

        assertEquals(210,result.getLength());
    }

    @Test
    void findMovieByNamePartWithoutResult(){
        assertThrows(IllegalArgumentException.class, ()->service.findMovieByNamePart("asd"));
    }
}