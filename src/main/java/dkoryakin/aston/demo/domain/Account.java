package dkoryakin.aston.demo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {

    private Long id;

    private String name;

    private Pin pin;

    private Double balance;

    public void deposit(Double amount) {
        balance +=amount;
    }

    public void withdraw(Double amount) {
        balance -= Math.abs(amount);
    }

    public void transfer(Double amount, Account destination) {
        balance -= amount;
        destination.balance +=amount;
    }
}
