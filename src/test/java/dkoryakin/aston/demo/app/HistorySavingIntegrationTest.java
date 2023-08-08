package dkoryakin.aston.demo.app;

import dkoryakin.aston.demo.api.body.AccountTransferPostBody;
import dkoryakin.aston.demo.api.body.response.HistoryGetResponseBody;
import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.OperationType;
import dkoryakin.aston.demo.domain.Pin;
import dkoryakin.aston.demo.domain.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HistorySavingIntegrationTest {


    @Autowired
    private AccountApplicationService accountApplicationService;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private HistoryApplicationService historyApplicationService;

    @Test
    public void shouldSaveCorrectlyTransfer_WhenTransferOperationWasDone() {
        Account accountA = Account.builder()
                .id(1L)
                .name("Dan")
                .pin(Pin.valueOf("1234"))
                .balance(100.0)
                .build();

        Account accountB = Account.builder()
                .id(1L)
                .name("Ben")
                .pin(Pin.valueOf("1234"))
                .balance(0.0)
                .build();

        Double transferAmount = 100.0;

        accountRepository.save(accountA);
        accountRepository.save(accountB);


        accountApplicationService.makeTransfer(accountA.getId(),
                new AccountTransferPostBody(accountA.getPin().getValue(), transferAmount, accountB.getId()));


        HistoryGetResponseBody transactions = historyApplicationService.getHistoryTransactionsByAccountId(accountA.getId());

        boolean isAllMatch = transactions.getEntries().stream().allMatch(entry ->
                entry.getType().equals(OperationType.TRANSFER) && entry.getSum().equals(transferAmount) &&
                entry.getIncomeAccountId().equals(accountA.getId()) && entry.getReceivingAccountId().equals(accountB.getId()));

        Assertions.assertTrue(isAllMatch);
    }

}
