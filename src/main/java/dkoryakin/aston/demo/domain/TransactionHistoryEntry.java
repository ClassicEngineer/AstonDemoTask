package dkoryakin.aston.demo.domain;

import dkoryakin.aston.demo.domain.event.OperationDoneEvent;
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


    public static TransactionHistoryEntry from(OperationDoneEvent event) {
        return TransactionHistoryEntry.builder()
                .date(event.getDate())
                .incomeAccountId(event.getAccountInId())
                .receivingAccountId(event.getAccountOutId())
                .sum(event.getSum())
                .type(event.getType())
                .build();
    }
}
