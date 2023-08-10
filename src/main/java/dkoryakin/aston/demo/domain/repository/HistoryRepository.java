package dkoryakin.aston.demo.domain.repository;

import dkoryakin.aston.demo.domain.TransactionHistoryEntry;
import dkoryakin.aston.demo.infrastructure.factory.TransactionHistoryFactory;
import dkoryakin.aston.demo.infrastructure.repository.JpaHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class HistoryRepository {

    private final JpaHistoryRepository jpaHistoryRepository;
    private final TransactionHistoryFactory factory;

    public void save(TransactionHistoryEntry entry) {
        jpaHistoryRepository.save(factory.buildEntityFromEntry(entry));
    }


    public Collection<TransactionHistoryEntry> findAllByAccountId(Long accountId) {
        return jpaHistoryRepository.findAllByAccountIdEquals(accountId)
                .stream()
                .map(factory::buildEntryFromEntity).toList();
    }


}
