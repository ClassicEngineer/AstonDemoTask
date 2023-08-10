package dkoryakin.aston.demo.domain.service;

import dkoryakin.aston.demo.app.result.Status;
import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.Pin;
import dkoryakin.aston.demo.domain.repository.AccountRepository;
import dkoryakin.aston.demo.infrastructure.factory.AccountFactory;
import dkoryakin.aston.demo.infrastructure.factory.OperationResultFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {


    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;


    @BeforeEach
    public void init() {
        var resultFactory = new OperationResultFactory(new AccountFactory());
        this.accountService = new AccountService(accountRepository, resultFactory);
    }

    @Test
    public void shouldCreateAccount_whenDataIsCorrect() {
        String name = "Dan";
        Pin pin = Pin.valueOf("1234");
        Account expected = Account.builder()
                .id(1L)
                .balance(0.0)
                .pin(pin)
                .name(name)
                .build();

        Mockito.when(accountRepository.create(name, pin)).thenReturn(expected);

        var result = accountService.createAccount(name, pin);


        Assertions.assertEquals(expected.getId(), result.getId());
        Assertions.assertEquals(expected.getName(), result.getName());
        Assertions.assertEquals(expected.getBalance(), result.getBalance());
        Assertions.assertEquals(expected.getPin(), result.getPin());
    }

    @Test
    public void shouldMakeDepositOperation_whenDataIsCorrect() {
        String name = "Dan";
        Pin pin = Pin.valueOf("1234");
        Account expected = Account.builder()
                .id(1L)
                .balance(0.0)
                .pin(pin)
                .name(name)
                .build();

        Double depositSum = 100.0;

        Mockito.when(accountRepository.findAccountById(expected.getId()))
                .thenReturn(Optional.of(expected));

        var result = accountService.makeDeposit(expected.getId(), depositSum);


        Assertions.assertEquals(Status.SUCCESS, result.getStatus());
        Assertions.assertEquals(depositSum, result.getResult().getBalance());
        Assertions.assertTrue(result.getEvent().isPresent());
    }


    @Test
    public void shouldNotMakeWithdraw_whenAmountIsNotCorrect() {
        Long accountId= 1L;
        Pin pin = Pin.valueOf("1234");
        Double incorrectAmount = 3000.0;
        Account expected = Account.builder()
                .id(1L)
                .balance(0.0)
                .pin(pin)
                .name("Dan")
                .build();

        Mockito.when(accountRepository.findAccountByIdAndPin(accountId, pin)).thenReturn(Optional.of(expected));

        var result = accountService.makeWithdraw(accountId, pin, incorrectAmount);

        Mockito.verify(accountRepository, Mockito.never()).save(Mockito.any());
        Assertions.assertEquals(Status.FAIL, result.getStatus());
    }


    @Test
    public void shouldNotMakeTransfer_whenOutcomeIdIsNotCorrect() {
        Long accountId= 1L;
        Pin pin = Pin.valueOf("1234");
        Double transferAmount = 5.0;
        Account expected = Account.builder()
                .id(1L)
                .balance(10.0)
                .pin(pin)
                .name("Dan")
                .build();

        Long incorrectTransferId = -999L;

        Mockito.when(accountRepository.findAccountByIdAndPin(accountId, pin)).thenReturn(Optional.of(expected));
        Mockito.when(accountRepository.findAccountById(incorrectTransferId)).thenReturn(Optional.empty());


        var result = accountService.makeTransfer(accountId, incorrectTransferId, pin, transferAmount);

        Mockito.verify(accountRepository, Mockito.never()).save(Mockito.any());
        Assertions.assertEquals(Status.FAIL, result.getStatus());
    }

    @Test
    public void shouldMakeTransferCorrectly_whenTransferDataIsNotCorrect() {
        Pin pin = Pin.valueOf("1234");
        Account accountA = Account.builder()
                .id(1L)
                .balance(100.0)
                .pin(pin)
                .name("Dan")
                .build();

        Account accountB = Account.builder()
                .id(2L)
                .balance(0.0)
                .pin(pin)
                .name("Ben")
                .build();
        Double transferAmount = 50.0;

        Mockito.when(accountRepository.findAccountByIdAndPin(accountA.getId(), accountA.getPin())).thenReturn(Optional.of(accountA));
        Mockito.when(accountRepository.findAccountById(accountB.getId())).thenReturn(Optional.of(accountB));
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);

        var result = accountService.makeTransfer(accountA.getId(), accountB.getId(), accountA.getPin(), transferAmount);

        Mockito.verify(accountRepository, times(2)).save(accountCaptor.capture());

        Account a = accountCaptor.getAllValues().stream().filter(account -> account.getId().equals(accountA.getId())).findFirst().get();
        Account b = accountCaptor.getAllValues().stream().filter(account -> account.getId().equals(accountB.getId())).findFirst().get();


        Assertions.assertEquals(50.0, a.getBalance());
        Assertions.assertEquals(50.0, b.getBalance());
        Assertions.assertEquals(Status.SUCCESS, result.getStatus());
    }

}