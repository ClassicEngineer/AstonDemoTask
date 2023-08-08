package dkoryakin.aston.demo.infrastructure.repository;

import dkoryakin.aston.demo.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByIdAndPin(Long accountId, String value);
}
