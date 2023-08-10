package dkoryakin.aston.demo.infrastructure.repository

import dkoryakin.aston.demo.infrastructure.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JpaAccountRepository : JpaRepository<AccountEntity, Long> {
    fun findByIdAndPin(accountId: Long, value: String): Optional<AccountEntity>
}
