package bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMoneyFlowCommand {

    private long moneyFlow;

    @JsonProperty(required = false)
    private String otherAccountNumber;
}
