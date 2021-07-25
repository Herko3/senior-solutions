package game.match;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NoMatchFoundException extends AbstractThrowableProblem {

    public NoMatchFoundException(long id) {
        super(URI.create("api/match-not-found"),
                "Match not found",
                Status.NOT_FOUND,
                "No match with id: " + id);
    }
}
