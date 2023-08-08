package dkoryakin.aston.demo.domain.repository;

import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.Pin;
import dkoryakin.aston.demo.infrastructure.repository.JpaAccountRepository;
import dkoryakin.aston.demo.infrastructure.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final JpaAccountRepository jpaAccountRepository;

    public Account create(String name, Pin pin) {
        var entity = AccountEntity.from(name, pin.getValue());
        entity = jpaAccountRepository.save(entity);
        return buildAccountFromEntity(entity);
    }

    public Account save(Account account) {
        var entity = buildEntityFromAccount(account);
        entity = jpaAccountRepository.save(entity);
        return buildAccountFromEntity(entity);
    }


    public Collection<Account> findAll() {
        return jpaAccountRepository.findAll()
                .stream()
                .map(this::buildAccountFromEntity)
                .toList();
    }

    public Optional<Account> findAccountByIdAndPin(Long accountId, Pin pin) {
        return jpaAccountRepository.findByIdAndPin(accountId, pin.getValue())
                .map(this::buildAccountFromEntity);
    }

    public Optional<Account> findAccountById(Long accountId) {
        return jpaAccountRepository.findById(accountId).map(this::buildAccountFromEntity);
    }

    private Account buildAccountFromEntity(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .name(entity.getName())
                .pin(Pin.valueOf(entity.getPin()))
                .balance(entity.getBalance())
                .build();
    }

    private AccountEntity buildEntityFromAccount(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .name(account.getName())
                .pin(account.getPin().getValue())
                .balance(account.getBalance())
                .build();
    }


}
