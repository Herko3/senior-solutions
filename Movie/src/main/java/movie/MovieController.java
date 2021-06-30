package movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovieDto> getMovies(@RequestParam Optional<String> title){
        return service.getMovies(title);
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable("id") long id){
        return service.getMovieById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto createMovie(@RequestBody CreateMovieCommand command){
        return service.createMovie(command);
    }

    @PostMapping("/{id}/rating")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MovieDto addRating(@PathVariable("id") long id, @RequestBody UpdateMovieRatingCommand command){
        return service.addRating(id,command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable("id") long id){
        service.deleteMovie(id);
    }

    @ExceptionHandler(MovieNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(MovieNotFound mnf){
        Problem problem = Problem.builder()
                .withType(URI.create("movies/not-found"))
                .withTitle("Not Found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(mnf.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
