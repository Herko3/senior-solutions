package library;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NoAuthorFoundException extends AbstractThrowableProblem {

    public NoAuthorFoundException(long id) {
        super(URI.create("api/author-not-found"),
                "not found",
                Status.NOT_FOUND,
                "author not found with id: " + id);
    }
}
