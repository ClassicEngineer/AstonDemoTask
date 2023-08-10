package dkoryakin.aston.demo.infrastructure.factory;

import dkoryakin.aston.demo.api.body.response.HistoryEntryView;
import dkoryakin.aston.demo.domain.TransactionHistoryEntry;
import dkoryakin.aston.demo.domain.event.OperationDoneEvent;
import dkoryakin.aston.demo.infrastructure.entity.TransactionHistoryEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionHistoryFactory {

    public  TransactionHistoryEntry buildEntryFromEvent(OperationDoneEvent event) {
        return TransactionHistoryEntry.builder()
                .date(event.getDate())
                .incomeAccountId(event.getAccountInId())
                .receivingAccountId(event.getAccountOutId())
                .sum(event.getSum())
                .type(event.getType())
                .build();
    }

    public TransactionHistoryEntity buildEntityFromEntry(TransactionHistoryEntry entry) {
        return TransactionHistoryEntity.builder()
                .date(entry.getDate())
                .type(entry.getType())
                .sum(entry.getSum())
                .accountId(entry.getIncomeAccountId())
                .receivingAccountId(entry.getReceivingAccountId())
                .build();
    }

    public TransactionHistoryEntry buildEntryFromEntity(TransactionHistoryEntity entity) {
        return TransactionHistoryEntry.builder()
                .date(entity.getDate())
                .type(entity.getType())
                .sum(entity.getSum())
                .incomeAccountId(entity.getAccountId())
                .receivingAccountId(entity.getReceivingAccountId())
                .build();
    }

    public HistoryEntryView buildViewFromEntry(TransactionHistoryEntry entry) {
        return HistoryEntryView.builder()
                .date(entry.getDate())
                .type(entry.getType())
                .incomeAccountId(entry.getIncomeAccountId())
                .receivingAccountId(entry.getReceivingAccountId())
                .sum(entry.getSum())
                .build();
    }
}
