package game.fighter;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.Nullable;
import java.net.URI;

public class NoFighterFoundException extends AbstractThrowableProblem {

    public NoFighterFoundException(long id) {
        super(URI.create("api/fighter-not-found"),
                "Fighter not found",
                Status.NOT_FOUND,
                "No fighter with id: " + id);
    }
}
