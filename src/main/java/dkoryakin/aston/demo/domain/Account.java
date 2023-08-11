package dkoryakin.aston.demo.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Account {

    private Long id;

    private String name;

    private Pin pin;

    private BigDecimal balance;

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount.abs());
    }

    public void transfer(BigDecimal amount, Account destination) {
        balance = balance.subtract(amount.abs());
        destination.balance = balance.add(amount);
    }
}
