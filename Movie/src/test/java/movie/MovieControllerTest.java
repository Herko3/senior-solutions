package movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Mock
    MovieService service;

    @InjectMocks
    MovieController controller;

    @Test
    void testCreateMovie(){
        when(service.createMovie(any())).thenReturn(new MovieDto("Titanic",120,4.5));

        MovieDto result = controller.createMovie(new CreateMovieCommand("Titanic",120));

        assertEquals("Titanic",result.getTitle());

        verify(service).createMovie(any());
    }


}