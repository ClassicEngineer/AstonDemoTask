package dkoryakin.aston.demo.infrastructure.repository;

import dkoryakin.aston.demo.infrastructure.entity.TransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface JpaHistoryRepository extends JpaRepository<TransactionHistoryEntity, Long> {

    Collection<TransactionHistoryEntity> findAllByAccountIdEquals(Long accountId);

    @Query(value =" SELECT DISTINCT history FROM TransactionHistoryEntity history WHERE history.receivingAccountId = :accountId OR history.accountId = :accountId")
    Collection<TransactionHistoryEntity> findAllByAccountIdAndReceivingAccountIdEquals(Long accountId);
}
