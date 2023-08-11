package dkoryakin.aston.demo.infrastructure.entity;

import dkoryakin.aston.demo.domain.history.SingleAccountOperationType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_history")
public class TransactionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SingleAccountOperationType type;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_date")
    private Date date;

    @Column(name = "account_id")
    private Long accountId;


    @Column(name = "operation_sum")
    private BigDecimal sum;
}
