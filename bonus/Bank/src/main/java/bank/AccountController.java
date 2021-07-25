package bank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<AccountDto> listAccounts() {
        return service.listAccounts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto saveAccount(@RequestBody CreateAccountCommand command) {
        return service.savaAccount(command);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountDto updateOwner(@PathVariable("id") long id, @RequestBody UpdateAccountOwnerCommand command) {
        return service.updateOwner(id,command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID(@PathVariable ("id") long id){
        service.deleteByID(id);
    }

    @GetMapping("/{id}")
    public AccountWithTransactionsDto getAccountInfo(@PathVariable ("id") long id){
        return service.getAccountInfo(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto moneyFlow(@PathVariable ("id") long id, @RequestBody CreateMoneyFlowCommand command){
        return service.moneyFlow(id,command);
    }

    @GetMapping("/{id}/cash")
    public List<TransactionDto> getCashFlow(@PathVariable ("id") long id){
        return service.getCashFlow(id);
    }

    @GetMapping("/{id}/transfer")
    public List<TransactionDto> getTransfers(@PathVariable ("id") long id){
        return service.getTransfers(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){
        service.deleteAll();
    }

}
