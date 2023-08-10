package dkoryakin.aston.demo.infrastructure.factory;

import dkoryakin.aston.demo.app.result.OperationResult;
import dkoryakin.aston.demo.app.result.Status;
import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.OperationType;
import dkoryakin.aston.demo.domain.event.OperationDoneEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationResultFactory {

    private final AccountFactory accountFactory;

    public OperationResult nonAuthorized() {
        return new OperationResult(Status.FAIL, "Non authorized to perform operation", null, null);
    }

    public OperationResult invalidOperation(String description) {
        return new OperationResult(Status.FAIL, description, null, null);

    }

    public OperationResult successDeposit(Account account, Double amount) {
        var event = OperationDoneEvent.builder()
                .accountInId(account.getId())
                .sum(amount)
                .type(OperationType.DEPOSIT)
                .build();
        return new OperationResult(Status.SUCCESS, "Successfully made deposit",
                event,
                accountFactory.buildViewFromAccount(account));

    }

    public OperationResult successWithdraw(Account account, Double amount) {
        var event = OperationDoneEvent.builder()
                .accountInId(account.getId())
                .sum(amount)
                .type(OperationType.WITHDRAW)
                .build();
        return new OperationResult(Status.SUCCESS, "Successfully made withdraw",
                event,
                accountFactory.buildViewFromAccount(account));
    }

    public OperationResult successTransfer(Account account, Double amount, Long transferToAccountId) {
        var event = OperationDoneEvent.builder()
                .accountInId(account.getId())
                .accountOutId(transferToAccountId)
                .sum(amount)
                .type(OperationType.TRANSFER)
                .build();
        return new OperationResult(Status.SUCCESS, "Successfully made transfer",
                event,
                accountFactory.buildViewFromAccount(account));
    }

}
