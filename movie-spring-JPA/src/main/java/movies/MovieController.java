package movies;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovieDto> listMovies(){
        return service.listMovies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto createMovie(@RequestBody CreateMovieCommand command){
        return service.createMovie(command);
    }

    @PostMapping("/{id}/rating")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MovieDto addRating(@PathVariable ("id") long id, @RequestBody CreateRatingCommand command){
        return service.addRating(id,command);
    }
}
