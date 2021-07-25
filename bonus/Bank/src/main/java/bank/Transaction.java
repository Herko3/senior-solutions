package bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private TransactionType type;
    private String ownerAccountNumber;
    private long moneyFlow;
    private LocalDateTime time;
    private long balanceAfter;
    private String otherAccountNumber;

    public Transaction(String ownerAccountNumber, long moneyFlow, LocalDateTime time, long balanceAfter, String otherAccountNumber) {
        this.ownerAccountNumber = ownerAccountNumber;
        this.moneyFlow = moneyFlow;
        this.time = time;
        this.balanceAfter = balanceAfter;
        this.otherAccountNumber = otherAccountNumber;
    }

    public Transaction(String ownerAccountNumber, long moneyFlow, LocalDateTime time, long balanceAfter) {
        this.ownerAccountNumber = ownerAccountNumber;
        this.moneyFlow = moneyFlow;
        this.time = time;
        this.balanceAfter = balanceAfter;
    }
}
