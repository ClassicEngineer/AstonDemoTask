package dkoryakin.aston.demo.domain.repository;

import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.Pin;
import dkoryakin.aston.demo.infrastructure.factory.AccountFactory;
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
    private final AccountFactory accountFactory;

    public Account create(String name, Pin pin) {
        var entity = accountFactory.buildEntityFromNameAndPin(name, pin.getValue());
        entity = jpaAccountRepository.save(entity);
        return accountFactory.buildAccountFromEntity(entity);
    }

    public Account save(Account account) {
        var entity = accountFactory.buildEntityFromAccount(account);
        entity = jpaAccountRepository.save(entity);
        return accountFactory.buildAccountFromEntity(entity);
    }


    public Collection<Account> findAll() {
        return jpaAccountRepository.findAll()
                .stream()
                .map(accountFactory::buildAccountFromEntity)
                .toList();
    }

    public Optional<Account> findAccountByIdAndPin(Long accountId, Pin pin) {
        return jpaAccountRepository.findByIdAndPin(accountId, pin.getValue())
                .map(accountFactory::buildAccountFromEntity);
    }

    public Optional<Account> findAccountById(Long accountId) {
        return jpaAccountRepository.findById(accountId).map(accountFactory::buildAccountFromEntity);
    }

}
