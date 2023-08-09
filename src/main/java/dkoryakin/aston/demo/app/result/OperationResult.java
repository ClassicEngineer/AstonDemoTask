package dkoryakin.aston.demo.app.result;

import dkoryakin.aston.demo.api.body.response.AccountView;
import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.event.OperationDoneEvent;
import dkoryakin.aston.demo.domain.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;


import java.util.Optional;


@Getter
@AllArgsConstructor
public class OperationResult {

    private Status status;
    private String description;
    private OperationDoneEvent event;
    private AccountView result;

    public static OperationResult nonAuthorized() {
        return new OperationResult(Status.FAIL, "Non authorized to perform operation", null, null);
    }

    public static OperationResult invalidRequest(String description) {
        return new OperationResult(Status.FAIL, description, null, null);

    }

    public static OperationResult successDeposit(Account account, Double amount) {
        var event = OperationDoneEvent.builder()
                .accountInId(account.getId())
                .sum(amount)
                .type(OperationType.DEPOSIT)
                .build();
        return new OperationResult(Status.SUCCESS, "Successfully made deposit",
                event,
                AccountView.from(account));

    }

    public static OperationResult successWithdraw(Account account, Double amount) {
        var event = OperationDoneEvent.builder()
                .accountInId(account.getId())
                .sum(amount)
                .type(OperationType.WITHDRAW)
                .build();
        return new OperationResult(Status.SUCCESS, "Successfully made withdraw",
                event,
                AccountView.from(account));
    }

    public static OperationResult successTransfer(Account account, Double amount, Long transferToAccountId) {
        var event = OperationDoneEvent.builder()
                .accountInId(account.getId())
                .accountOutId(transferToAccountId)
                .sum(amount)
                .type(OperationType.TRANSFER)
                .build();
        return new OperationResult(Status.SUCCESS, "Successfully made transfer",
                event,
                AccountView.from(account));
    }


    public Optional<OperationDoneEvent> getEvent() {
        return Optional.ofNullable(event);
    }


    public ResponseEntity<?> toResponse() {
        return switch (status) {
            case SUCCESS -> ResponseEntity.ok(result);
            case FAIL -> ResponseEntity.badRequest().body(description);
        };
    }
}
