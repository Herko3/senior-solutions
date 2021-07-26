package movies;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository repository;

    private ModelMapper mapper;

    public MovieService(MovieRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MovieDto> listMovies() {
        return repository.findAll().stream()
                .map(m->mapper.map(m,MovieDto.class))
                .toList();
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        repository.save(movie);
        return mapper.map(movie,MovieDto.class);
    }

    public MovieDto addRating(long id, CreateRatingCommand command) {
        Movie movie = repository.getById(id);
        movie.addRating(command.getRating());
        repository.save(movie);
        return mapper.map(movie,MovieDto.class);
    }
}
