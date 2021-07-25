package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account;

    @BeforeEach
    void init() {
        account = new Account("Jack Doe", "acc_number", LocalDateTime.now());
    }

    @Test
    void testPositiveCashFlow() {

        account.createTransaction(100);

        assertEquals(100, account.getBalance());

        assertThat(account.getTransactions())
                .hasSize(1)
                .extracting(Transaction::getType)
                .containsOnly(TransactionType.CASH_IN);
    }

    @Test
    void testNegativeCashFlow() {

        account.createTransaction(200);
        account.createTransaction(-100);

        assertEquals(100, account.getBalance());

        assertThat(account.getTransactions())
                .hasSize(2)
                .extracting(Transaction::getType)
                .containsExactly(TransactionType.CASH_IN, TransactionType.CASH_OUT);
    }

    @Test
    void testInvalidFlow() {

        assertThrows(NotEnoughBalance.class, () -> account.checkEnoughBalance(-200));
    }

    @Test
    void testTransferOut(){
        account.createTransaction(-200,"other");

        assertEquals(-200,account.getBalance());

        assertThat(account.getTransactions())
        .hasSize(1)
        .extracting(Transaction::getType)
        .containsOnly(TransactionType.TRANSFER_OUT);
    }

    @Test
    void testTransferIn(){
        account.createTransaction(200,"other");

        assertEquals(200,account.getBalance());

        assertThat(account.getTransactions())
                .hasSize(1)
                .extracting(Transaction::getType)
                .containsOnly(TransactionType.TRANSFER_IN);
    }

}