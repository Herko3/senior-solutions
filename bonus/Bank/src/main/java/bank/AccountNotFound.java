package bank;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class AccountNotFound extends AbstractThrowableProblem {

    public AccountNotFound(long id) {
        super(URI.create("accounts/not-found"),
                "not found",
                Status.NOT_FOUND,
                "account not found with id: " + id);
    }

    public AccountNotFound(String number) {
        super(URI.create("accounts/not-found"),
                "not found",
                Status.NOT_FOUND,
                "account not found with account number: " + number);
    }
}
