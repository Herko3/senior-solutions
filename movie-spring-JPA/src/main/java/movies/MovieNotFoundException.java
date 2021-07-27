package movies;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class MovieNotFoundException extends AbstractThrowableProblem {

    public MovieNotFoundException(long id) {
        super(URI.create("movie/not-found"),
                "not found",
                Status.NOT_FOUND,
                "Movie not found with id: " + id);
    }
}
