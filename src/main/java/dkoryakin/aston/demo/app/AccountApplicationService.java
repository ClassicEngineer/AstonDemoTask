package dkoryakin.aston.demo.app;

import dkoryakin.aston.demo.api.body.*;
import dkoryakin.aston.demo.api.body.response.AccountView;
import dkoryakin.aston.demo.app.result.AccountCreationResult;
import dkoryakin.aston.demo.api.body.response.AccountsGetResponseBody;
import dkoryakin.aston.demo.app.result.OperationResult;
import dkoryakin.aston.demo.domain.service.AccountService;
import dkoryakin.aston.demo.domain.Pin;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountApplicationService {

    private final AccountService accountService;

    private final ApplicationEventPublisher eventPublisher;

    public AccountCreationResult createAccount(AccountCreatePostBody body) {
        var account = accountService.createAccount(body.getName(), Pin.valueOf(body.getPin()));
        return AccountCreationResult.success(AccountView.from(account));
    }

    public AccountsGetResponseBody getAllAccounts() {
        var views = accountService.getAllAccounts().stream()
                .map(AccountView::from)
                .toList();
        return new AccountsGetResponseBody(views);
    }

    public OperationResult makeDeposit(Long accountId, AccountDepositPostBody body) {
        var result = accountService.makeDeposit(accountId, body.getAmount());
        result.getEvent().ifPresent(eventPublisher::publishEvent);
        return result;
    }


    public OperationResult makeWithdraw(Long accountId, AccountWithdrawPostBody body) {
        var result = accountService.makeWithdraw(accountId, Pin.valueOf(body.getPin()), body.getAmount());
        result.getEvent().ifPresent(eventPublisher::publishEvent);
        return result;
    }

    public OperationResult makeTransfer(Long accountId, AccountTransferPostBody body) {
        var result = accountService.makeTransfer(accountId, body.getAccountId(), Pin.valueOf(body.getPin()), body.getAmount());
        result.getEvent().ifPresent(eventPublisher::publishEvent);
        return result;
    }


}
