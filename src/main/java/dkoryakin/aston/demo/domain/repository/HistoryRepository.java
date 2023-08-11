package dkoryakin.aston.demo.domain.repository;

import dkoryakin.aston.demo.domain.history.TransactionHistoryEntry;
import dkoryakin.aston.demo.domain.history.TransactionTransferHistoryEntry;
import dkoryakin.aston.demo.infrastructure.factory.TransactionHistoryFactory;
import dkoryakin.aston.demo.infrastructure.repository.JpaHistoryRepository;
import dkoryakin.aston.demo.infrastructure.repository.JpaHistoryTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class HistoryRepository {

    private final JpaHistoryRepository jpaHistoryRepository;
    private final JpaHistoryTransferRepository jpaHistoryTransferRepository;
    private final TransactionHistoryFactory factory;

    public void save(TransactionHistoryEntry entry) {
        jpaHistoryRepository.save(factory.buildEntityFromEntry(entry));
    }

    public void save(TransactionTransferHistoryEntry entry) {
        jpaHistoryTransferRepository.save(factory.buildEntityFromEntry(entry));
    }


    public Collection<TransactionHistoryEntry> findAllSingleByAccountId(Long accountId) {
        return jpaHistoryRepository.findAllByAccountIdEquals(accountId)
                .stream()
                .map(factory::buildEntryFromEntity).toList();
    }


    public Collection<TransactionTransferHistoryEntry> findAllTransferByAccountId(Long accountId) {
        return jpaHistoryTransferRepository.findAllByAccountIdEquals(accountId)
                .stream()
                .map(factory::buildTransferEntryFromEntity).toList();
    }
}
