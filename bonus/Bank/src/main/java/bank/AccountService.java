package bank;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AccountService {

    private final AccountRepository repository;

    private ModelMapper mapper;

    private AtomicLong accountNumber = new AtomicLong(10000000);

    public AccountService(AccountRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AccountDto savaAccount(CreateAccountCommand command) {
        Account account = repository.saveAccount(new Account(command.getOwner(), String.valueOf(accountNumber.incrementAndGet()), LocalDateTime.now()));
        return mapper.map(account, AccountDto.class);
    }

    public AccountDto updateOwner(long id, UpdateAccountOwnerCommand command) {
        Account account = repository.updateOwner(id, command.getOwner());
        return mapper.map(account, AccountDto.class);
    }

    public void deleteByID(long id) {
        repository.deleteByID(id);
    }

    public List<AccountDto> listAccounts() {
        return repository.getAccounts().stream()
                .map(a -> mapper.map(a, AccountDto.class))
                .toList();
    }


    public AccountWithTransactionsDto getAccountInfo(long id) {
        Account account = repository.findByID(id);

        return mapper.map(account, AccountWithTransactionsDto.class);
    }

    public AccountDto moneyFlow(long id, CreateMoneyFlowCommand command) {
        Account account = repository.findByID(id);
        account.checkEnoughBalance(command.getMoneyFlow());

        if (command.getOtherAccountNumber() != null) {
            Account another = repository.findByAccountNumber(command.getOtherAccountNumber());
            return transfer(account, another, command);
        }

        return cashFlow(account, command);
    }

    private AccountDto cashFlow(Account account, CreateMoneyFlowCommand command) {

        account.createTransaction(command.getMoneyFlow());
        return mapper.map(account, AccountDto.class);
    }

    private AccountDto transfer(Account from, Account to, CreateMoneyFlowCommand command) {
        long flow = command.getMoneyFlow();

        from.createTransaction(flow, to.getAccountNumber());
        to.createTransaction(-flow, from.getAccountNumber());

        return mapper.map(from, AccountDto.class);
    }

    public List<TransactionDto> getCashFlow(long id) {
        List<Transaction> transactions = repository.findByID(id).getTransactions();

        return transactions.stream()
                .filter(t->t.getType() == TransactionType.CASH_IN || t.getType() == TransactionType.CASH_OUT)
                .map(t->mapper.map(t,TransactionDto.class))
                .toList();
    }

    public List<TransactionDto> getTransfers(long id) {
        List<Transaction> transactions = repository.findByID(id).getTransactions();

        return transactions.stream()
                .filter(t->t.getType() == TransactionType.TRANSFER_IN || t.getType() == TransactionType.TRANSFER_OUT)
                .map(t->mapper.map(t,TransactionDto.class))
                .toList();
    }

    public void deleteAll() {
        repository.deleteAll();
        accountNumber = new AtomicLong(10000000);
    }
}
