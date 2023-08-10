package dkoryakin.aston.demo.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Builder
@Getter
public class TransactionHistoryEntry {


    private OperationType type;

    private Date date;

    private Long incomeAccountId;

    private Long receivingAccountId;

    private Double sum;

}
