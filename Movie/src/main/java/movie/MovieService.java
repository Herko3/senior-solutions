package movie;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieService {

    private ModelMapper mapper;
    private List<Movie> movies = new ArrayList<>();
    private AtomicLong generateId = new AtomicLong();

    public MovieService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<MovieDto> getMovies(Optional<String> title) {
        return movies.stream()
                .filter(m-> title.isEmpty() || m.getTitle().toLowerCase().contains(title.get().toLowerCase()))
                .map(m->mapper.map(m,MovieDto.class))
                .toList();
    }

    public MovieDto getMovieById(long id) {
        return mapper.map(findById(id),MovieDto.class);
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(generateId.incrementAndGet(),command.getTitle(), command.getLength());
        movies.add(movie);
        return mapper.map(movie,MovieDto.class);
    }

    public MovieDto addRating(long id, UpdateMovieRatingCommand command) {
        Movie movie = findById(id);

        movie.addRating(command.getRating());

        return mapper.map(movie,MovieDto.class);
    }

    public void deleteMovie(long id) {
        Movie movie = findById(id);

        movies.remove(movie);
    }

    private Movie findById(long id){
        return movies.stream()
                .filter(m->m.getId() == id)
                .findAny()
                .orElseThrow(()->new MovieNotFound("No movie with id: " + id));
    }
}
