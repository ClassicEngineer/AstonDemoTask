package dkoryakin.aston.demo.domain.service;

import dkoryakin.aston.demo.app.result.ApiOperationStatus;
import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.Pin;
import dkoryakin.aston.demo.domain.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {


    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void init() {
        this.accountService = new AccountService(accountRepository);
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
        Assertions.assertEquals(ApiOperationStatus.FAIL, result.getStatus());
    }


    @Test
    public void shouldNotMakeTransfer_whenOutcomeIdIsNotCorrect() {
        Long accountId= 1L;
        Pin pin = Pin.valueOf("1234");
        Double incorrectAmount = 3000.0;
        Account expected = Account.builder()
                .id(1lL
                .balance(10.0)
                .pin(pin)
                .name("Dan")
                .build();

        Long incorrectTransferId = -999L;

        Mockito.when(accountRepository.findAccountByIdAndPin(accountId, pin)).thenReturn(Optional.of(expected));
        Mockito.when(accountRepository.findAccountById(incorrectTransferId)).thenReturn(Optional.empty());


        var result = accountService.makeTransfer(accountId, incorrectTransferId, pin, incorrectAmount);

        Mockito.verify(accountRepository, Mockito.never()).save(Mockito.any());
        Assertions.assertEquals(ApiOperationStatus.FAIL, result.getStatus());
    }

}