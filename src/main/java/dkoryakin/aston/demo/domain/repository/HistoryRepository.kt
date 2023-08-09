package dkoryakin.aston.demo.domain.repository

import dkoryakin.aston.demo.domain.TransactionHistoryEntry
import dkoryakin.aston.demo.infrastructure.entity.TransactionHistoryEntity
import dkoryakin.aston.demo.infrastructure.repository.JpaHistoryRepository
import org.springframework.stereotype.Repository

@Repository
class HistoryRepository(private val jpaHistoryRepository: JpaHistoryRepository) {
    fun save(entry: TransactionHistoryEntry) {
        jpaHistoryRepository.save(buildEntityFromEntry(entry))
    }

    fun findAllByAccountId(accountId: Long): Collection<TransactionHistoryEntry> {
        return jpaHistoryRepository.findAllByAccountIdEquals(accountId)
                .stream()
                .map { entity: TransactionHistoryEntity -> buildEntryFromEntity(entity) }
                .toList()
    }

    private fun buildEntityFromEntry(entry: TransactionHistoryEntry): TransactionHistoryEntity {
        return TransactionHistoryEntity.builder()
                .date(entry.date)
                .type(entry.type)
                .sum(entry.sum)
                .accountId(entry.incomeAccountId)
                .receivingAccountId(entry.receivingAccountId)
                .build()
    }

    private fun buildEntryFromEntity(entity: TransactionHistoryEntity): TransactionHistoryEntry {
        return TransactionHistoryEntry(
                date = entity.date,
                type = entity.type,
                sum = entity.sum,
                incomeAccountId = entity.accountId,
                receivingAccountId = entity.receivingAccountId,
        )
    }
}
