package dkoryakin.aston.demo.domain.service;

import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.repository.AccountRepository;
import dkoryakin.aston.demo.app.result.OperationResult;
import dkoryakin.aston.demo.domain.Pin;
import dkoryakin.aston.demo.infrastructure.factory.OperationResultFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final OperationResultFactory resultFactory;

    public Account createAccount(String name, Pin pin) {
        return accountRepository.create(name, pin);
    }

    @Transactional(readOnly = true)
    public Collection<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Optional<Account> findAccountByIdAndPin(Long accountId, Pin pin) {
        return accountRepository.findAccountByIdAndPin(accountId, pin);
    }


    public OperationResult makeDeposit(Long accountId, Double amount) {
        Optional<Account> accountOptional = findAccountById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (validateDeposit(amount)) {
                account.deposit(amount);
                save(account);
                return resultFactory.successDeposit(account, amount);
            } else {
                return resultFactory.invalidOperation("Deposit must me positive");
            }
        } else {
            return resultFactory.nonAuthorized();
        }
    }


    public OperationResult makeWithdraw(Long accountId, Pin pin, Double amount) {
        Optional<Account> accountOptional = findAccountByIdAndPin(accountId, pin);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (validateWithdraw(amount, account.getBalance())) {
                account.withdraw(amount);
                save(account);
                return resultFactory.successWithdraw(account, amount);
            } else {
                return resultFactory.invalidOperation("Withdraw must me less or equal balance");
            }
        } else {
            return resultFactory.nonAuthorized();
        }
    }

    public OperationResult makeTransfer(Long accountId, Long transferToAccountId, Pin pin, Double amount) {
        Optional<Account> accountOptional = findAccountByIdAndPin(accountId, pin);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            Optional<Account> destinationAccount = findAccountById(transferToAccountId);
            if (validateTransfer(amount, account.getBalance())) {
                if (destinationAccount.isPresent()) {
                    Account destination = destinationAccount.get();
                    account.transfer(amount, destination);
                    save(account);
                    save(destination);
                } else {
                    return resultFactory.invalidOperation("Account with id: " + transferToAccountId +" is not exist.");
                }
                return resultFactory.successTransfer(account, amount, transferToAccountId);
            } else {
                return resultFactory.invalidOperation("Transfer sum must be less or equal balance");
            }
        } else {
            return resultFactory.nonAuthorized();
        }
    }

    private Optional<Account> findAccountById(Long accountId) {
        return accountRepository.findAccountById(accountId);
    }

    private boolean validateTransfer(Double amount, Double balance) {
        return amount > 0 && amount <= balance;
    }


    private boolean validateDeposit(Double amount) {
        return amount > 0;
    }

    private boolean validateWithdraw(Double amount, Double balance) {
        return Math.abs(amount) <= balance;
    }

    private void save(Account account) {
        accountRepository.save(account);
    }

}
