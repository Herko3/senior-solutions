package locations;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class LocationNotFound extends AbstractThrowableProblem {

    public LocationNotFound(long id) {
        super(URI.create("locations/not-found"),
                "not found",
                Status.NOT_FOUND,
                "location not found with id: " + id);
    }
}
