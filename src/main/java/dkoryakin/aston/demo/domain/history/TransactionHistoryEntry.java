package dkoryakin.aston.demo.domain.history;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
@Builder
@Getter
public class TransactionHistoryEntry {

    private SingleAccountOperationType type;

    private Date date;

    private Long accountId;

    private BigDecimal sum;

}
