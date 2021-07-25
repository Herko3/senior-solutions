package bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountWithTransactionsDto {

    private String owner;
    private String accountNumber;
    private long balance;
    private List<Transaction> transactions;
}
