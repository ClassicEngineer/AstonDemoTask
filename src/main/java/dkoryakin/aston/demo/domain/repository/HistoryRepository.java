package dkoryakin.aston.demo.domain.repository;

import dkoryakin.aston.demo.domain.TransactionHistoryEntry;
import dkoryakin.aston.demo.infrastructure.entity.TransactionHistoryEntity;
import dkoryakin.aston.demo.infrastructure.repository.JpaHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class HistoryRepository {

    private final JpaHistoryRepository jpaHistoryRepository;

    public void save(TransactionHistoryEntry entry) {
        jpaHistoryRepository.save(buildEntityFromEntry(entry));
    }


    public Collection<TransactionHistoryEntry> findAllByAccountId(Long accountId) {
        return jpaHistoryRepository.findAllByIncomeAccountIdEquals(accountId)
                .stream()
                .map(this::buildEntryFromEntity).toList();
    }

    private TransactionHistoryEntity buildEntityFromEntry(TransactionHistoryEntry entry) {
        return TransactionHistoryEntity.builder()
                .date(entry.getDate())
                .type(entry.getType())
                .sum(entry.getSum())
                .incomeAccountId(entry.getIncomeAccountId())
                .receivingAccountId(entry.getReceivingAccountId())
                .build();
    }

    private TransactionHistoryEntry buildEntryFromEntity(TransactionHistoryEntity entity) {
        return TransactionHistoryEntry.builder()
                .date(entity.getDate())
                .type(entity.getType())
                .sum(entity.getSum())
                .incomeAccountId(entity.getIncomeAccountId())
                .receivingAccountId(entity.getReceivingAccountId())
                .build();
    }
}
