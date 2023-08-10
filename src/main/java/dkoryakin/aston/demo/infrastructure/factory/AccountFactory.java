package dkoryakin.aston.demo.infrastructure.factory;

import dkoryakin.aston.demo.api.body.response.AccountView;
import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.Pin;
import dkoryakin.aston.demo.infrastructure.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    public Account buildAccountFromEntity(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .name(entity.getName())
                .pin(Pin.valueOf(entity.getPin()))
                .balance(entity.getBalance())
                .build();
    }

    public AccountEntity buildEntityFromAccount(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .name(account.getName())
                .pin(account.getPin().getValue())
                .balance(account.getBalance())
                .build();
    }

    public AccountView buildViewFromAccount(Account account) {
        return AccountView.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .name(account.getName())
                .build();
    }
}
