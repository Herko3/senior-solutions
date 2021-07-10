package movie;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    public void saveMovie(Movie movie) {
        movies.add(movie);
    }

    public Movie findLatestMovie() {
        return movies.stream().max(Comparator.comparing(Movie::getReleaseDate))
                .orElseThrow(()->new IllegalStateException("Movies list is empty"));
    }

    public Movie findMovieByNamePart(String part) {
        return movies.stream()
                .filter(m -> m.getName().contains(part))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No movie with that namepart: " + part));
    }
}
