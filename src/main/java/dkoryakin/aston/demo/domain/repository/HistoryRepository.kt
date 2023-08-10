package dkoryakin.aston.demo.domain.repository

import dkoryakin.aston.demo.domain.TransactionHistoryEntry
import dkoryakin.aston.demo.infrastructure.entity.TransactionHistoryEntity
import dkoryakin.aston.demo.infrastructure.factory.TransactionHistoryFactory
import dkoryakin.aston.demo.infrastructure.repository.JpaHistoryRepository
import org.springframework.stereotype.Repository

@Repository
class HistoryRepository(
    private val jpaHistoryRepository: JpaHistoryRepository,
    private val factory: TransactionHistoryFactory
) {
    fun save(entry: TransactionHistoryEntry?) {
        jpaHistoryRepository.save(factory.buildEntityFromEntry(entry))
    }

    fun findAllByAccountId(accountId: Long?): Collection<TransactionHistoryEntry> {
        return jpaHistoryRepository.findAllByAccountIdEquals(accountId)
            .stream()
            .map { entity: TransactionHistoryEntity? -> factory.buildEntryFromEntity(entity) }.toList()
    }
}
