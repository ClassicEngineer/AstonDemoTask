package dkoryakin.aston.demo.api.body.response;

import dkoryakin.aston.demo.domain.OperationType;
import dkoryakin.aston.demo.domain.TransactionHistoryEntry;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HistoryEntryView {

    private OperationType type;

    private Date date;

    private Long incomeAccountId;

    private Long receivingAccountId;

    private Double sum;

    public static HistoryEntryView from(TransactionHistoryEntry entry) {
        return HistoryEntryView.builder()
                .date(entry.getDate())
                .type(entry.getType())
                .incomeAccountId(entry.getIncomeAccountId())
                .receivingAccountId(entry.getReceivingAccountId())
                .sum(entry.getSum())
                .build();
    }
}
