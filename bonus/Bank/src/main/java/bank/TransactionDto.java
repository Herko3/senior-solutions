package bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private TransactionType type;
    private String ownerAccountNumber;
    private long moneyFlow;
    private LocalDateTime time;
    private long balanceAfter;
    private String otherAccountNumber;

}
