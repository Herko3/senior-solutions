package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerIT {

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void init() {
        template.delete("/api/accounts");
        template.postForObject("/api/accounts", new CreateAccountCommand("Jack Doe"), AccountDto.class);
        template.postForObject("/api/accounts", new CreateAccountCommand("John Doe"), AccountDto.class);

    }

    @Test
    void testSave() {

        AccountDto result = template.postForObject("/api/accounts", new CreateAccountCommand("Jack Doe"), AccountDto.class);

        assertEquals(0, result.getBalance());
        assertEquals("Jack Doe", result.getOwner());
    }

    @Test
    void testList() {
        template.postForObject("/api/accounts", new CreateAccountCommand("Jane Doe"), AccountDto.class);

        List<AccountDto> result = template.exchange("/api/accounts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(3)
                .extracting(AccountDto::getOwner)
                .contains("Jane Doe");
    }

    @Test
    void updateOwner() {
        template.postForObject("/api/accounts", new CreateAccountCommand("Jane Doe"), AccountDto.class);

        AccountWithTransactionsDto preUpdate = template.getForObject("/api/accounts/3", AccountWithTransactionsDto.class);
        assertEquals("Jane Doe", preUpdate.getOwner());

        template.put("/api/accounts/3", new UpdateAccountOwnerCommand("Jack Doe"));
        AccountWithTransactionsDto postUpdate = template.getForObject("/api/accounts/3", AccountWithTransactionsDto.class);
        assertEquals("Jack Doe", postUpdate.getOwner());
    }

    @Test
    void deleteByID() {
        AccountDto account = template.postForObject("/api/accounts", new CreateAccountCommand("Jane Doe"), AccountDto.class);
        List<AccountDto> preDelete = template.exchange("/api/accounts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                })
                .getBody();

        assertThat(preDelete)
                .hasSize(3)
                .extracting(AccountDto::getOwner)
                .contains("Jane Doe");

        template.delete("/api/accounts/3");

        List<AccountDto> postDelete = template.exchange("/api/accounts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                })
                .getBody();

        assertThat(postDelete)
                .hasSize(2)
                .extracting(AccountDto::getOwner)
                .doesNotContain("Jane Doe");
    }

    @Test
    void testMoneyFlowWithPositiveCash() {
        template.postForObject("/api/accounts/1", new CreateMoneyFlowCommand(300, null), AccountDto.class);

        AccountWithTransactionsDto result = template.getForObject("/api/accounts/1", AccountWithTransactionsDto.class);

        assertEquals(300, result.getBalance());
        assertThat(result.getTransactions())
                .hasSize(1);
    }

    @Test
    void testMoneyFlowWithNegativeCash() {
        template.postForObject("/api/accounts/1", new CreateMoneyFlowCommand(300, null), AccountDto.class);
        template.postForObject("/api/accounts/1", new CreateMoneyFlowCommand(-200, null), AccountDto.class);

        AccountWithTransactionsDto result = template.getForObject("/api/accounts/1", AccountWithTransactionsDto.class);

        assertEquals(100, result.getBalance());
        assertThat(result.getTransactions())
                .hasSize(2);
    }

    @Test
    void testTransfer() {
        template.postForObject("/api/accounts/1", new CreateMoneyFlowCommand(300, null), AccountDto.class);
        template.postForObject("/api/accounts/1", new CreateMoneyFlowCommand(-300, "10000002"), AccountDto.class);

        AccountWithTransactionsDto from = template.getForObject("/api/accounts/1", AccountWithTransactionsDto.class);
        AccountWithTransactionsDto to = template.getForObject("/api/accounts/2", AccountWithTransactionsDto.class);

        assertEquals(0, from.getBalance());
        assertEquals(300, to.getBalance());
    }

    @Test
    void testInvalidMoneyFlow() {
        Problem problem = template.postForObject("/api/accounts/1", new CreateMoneyFlowCommand(-300, null), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
    }

    @Test
    void testNotFound() {
        Problem problem = template.getForObject("/api/accounts/100", Problem.class);

        assertEquals(Status.NOT_FOUND, problem.getStatus());
    }

}