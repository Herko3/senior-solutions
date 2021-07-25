package bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;

    private String owner;
    private String accountNumber;
    private long balance;
    private LocalDateTime created;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber, LocalDateTime created) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.created = created;
    }

    public void createTransaction(long flow) {
        Transaction transaction = new Transaction(accountNumber, flow, LocalDateTime.now(), balance + flow);
        if (flow < 0) {
            transaction.setType(TransactionType.CASH_OUT);
        } else {
            transaction.setType(TransactionType.CASH_IN);
        }
        transactions.add(transaction);
        balance = transaction.getBalanceAfter();
    }

    public void createTransaction(long flow, String otherAccount) {
        Transaction transaction = new Transaction(accountNumber, flow, LocalDateTime.now(), balance + flow, otherAccount);
        if (flow < 0) {
            transaction.setType(TransactionType.TRANSFER_OUT);
        } else {
            transaction.setType(TransactionType.TRANSFER_IN);
        }
        transactions.add(transaction);
        balance = transaction.getBalanceAfter();
    }

    public void checkEnoughBalance(long flow) {
        if (flow + balance < 0) {
            throw new NotEnoughBalance();
        }
    }
}
