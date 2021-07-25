package bank;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NotEnoughBalance extends AbstractThrowableProblem {

    public NotEnoughBalance() {
        super(URI.create("accounts/not-enough-balance"),
                "not enough balance",
                Status.BAD_REQUEST,
                "not enough balance");
    }
}
