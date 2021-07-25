package bank;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccountRepository {

    private List<Account> accounts = new ArrayList<>();

    private ModelMapper modelMapper;

    private AtomicLong idGen = new AtomicLong(0);

    public AccountRepository(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Account saveAccount(Account account){
        account.setId(idGen.incrementAndGet());
        accounts.add(account);

        return account;
    }

    public Account updateOwner(long id, String owner) {
        Account account = findByID(id);
        account.setOwner(owner);
        return account;
    }

    public Account findByID(long id){
        return accounts.stream()
                .filter(a->a.getId() == id)
                .findAny()
                .orElseThrow(()-> new AccountNotFound(id));
    }

    public void deleteByID(long id) {
        accounts.remove(findByID(id));
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account findByAccountNumber(String accountNumber){
        return accounts.stream()
                .filter(a->a.getAccountNumber().equals(accountNumber))
                .findAny()
                .orElseThrow(()-> new AccountNotFound(accountNumber));
    }

    public void deleteAll() {
        accounts.clear();
        idGen = new AtomicLong(0);
    }
}
