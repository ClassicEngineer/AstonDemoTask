package dkoryakin.aston.demo.infrastructure.repository;

import dkoryakin.aston.demo.infrastructure.entity.TransactionTransferHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JpaHistoryTransferRepository extends JpaRepository<TransactionTransferHistoryEntity, Long> {
    Collection<TransactionTransferHistoryEntity> findAllByAccountIdEquals(Long accountId);
}
