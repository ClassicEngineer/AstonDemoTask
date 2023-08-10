package dkoryakin.aston.demo.domain.repository

import dkoryakin.aston.demo.domain.Account
import dkoryakin.aston.demo.domain.Pin
import dkoryakin.aston.demo.infrastructure.entity.AccountEntity
import dkoryakin.aston.demo.infrastructure.factory.AccountFactory
import dkoryakin.aston.demo.infrastructure.repository.JpaAccountRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@RequiredArgsConstructor
class AccountRepository(private val jpaAccountRepository: JpaAccountRepository,
                        private val accountFactory: AccountFactory) {

    fun create(name: String?, pin: Pin): Account {
        var entity = AccountEntity.from(name, pin.value)
        entity = jpaAccountRepository.save(entity)
        return accountFactory.buildAccountFromEntity(entity)
    }

    fun save(account: Account?): Account {
        var entity = accountFactory.buildEntityFromAccount(account)
        entity = jpaAccountRepository.save(entity)
        return accountFactory.buildAccountFromEntity(entity)
    }

    fun findAll(): Collection<Account> {
        return jpaAccountRepository.findAll()
                .stream()
                .map { entity: AccountEntity -> accountFactory.buildAccountFromEntity(entity) }
                .toList()
    }

    fun findAccountByIdAndPin(accountId: Long?, pin: Pin): Optional<Account> {
        return jpaAccountRepository.findByIdAndPin(accountId, pin.value)
                .map { entity: AccountEntity -> accountFactory.buildAccountFromEntity(entity) }
    }

    fun findAccountById(accountId: Long): Optional<Account> {
        return jpaAccountRepository.findById(accountId).map { entity: AccountEntity -> accountFactory.buildAccountFromEntity(entity) }
    }
}
