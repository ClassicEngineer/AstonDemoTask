package dkoryakin.aston.demo.infrastructure.factory;

import dkoryakin.aston.demo.api.body.response.HistoryEntryView;
import dkoryakin.aston.demo.domain.OperationType;
import dkoryakin.aston.demo.domain.history.SingleAccountOperationType;
import dkoryakin.aston.demo.domain.history.TransactionHistoryEntry;
import dkoryakin.aston.demo.domain.history.TransactionTransferHistoryEntry;
import dkoryakin.aston.demo.domain.event.OperationDoneEvent;
import dkoryakin.aston.demo.infrastructure.entity.TransactionHistoryEntity;
import dkoryakin.aston.demo.infrastructure.entity.TransactionTransferHistoryEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionHistoryFactory {

    public TransactionTransferHistoryEntity buildEntityFromEntry(TransactionTransferHistoryEntry entry) {
        return TransactionTransferHistoryEntity.builder()
                .date(entry.getDate())
                .sum(entry.getSum())
                .accountId(entry.getOutcomeAccountId())
                .receivingAccountId(entry.getReceivingAccountId())
                .build();
    }

    public  TransactionHistoryEntry buildEntryFromEvent(OperationDoneEvent event) {
        return TransactionHistoryEntry.builder()
                .date(event.getDate())
                .accountId(event.getAccountId())
                .sum(event.getSum())
                .type(SingleAccountOperationType.valueOf(event.getType().name()))
                .build();
    }

    public  TransactionTransferHistoryEntry buildTransferEntryFromEvent(OperationDoneEvent event) {
        return TransactionTransferHistoryEntry.builder()
                .date(event.getDate())
                .outcomeAccountId(event.getAccountId())
                .receivingAccountId(event.getReceivingAccountId())
                .sum(event.getSum())
                .build();
    }

    public TransactionHistoryEntity buildEntityFromEntry(TransactionHistoryEntry entry) {
        return TransactionHistoryEntity.builder()
                .date(entry.getDate())
                .type(entry.getType())
                .sum(entry.getSum())
                .accountId(entry.getAccountId())
                .build();
    }

    public TransactionHistoryEntry buildEntryFromEntity(TransactionHistoryEntity entity) {
        return TransactionHistoryEntry.builder()
                .accountId(entity.getAccountId())
                .date(entity.getDate())
                .type(entity.getType())
                .sum(entity.getSum())
                .build();
    }

    public TransactionTransferHistoryEntry buildTransferEntryFromEntity(TransactionTransferHistoryEntity entity) {
        return TransactionTransferHistoryEntry.builder()
                .date(entity.getDate())
                .outcomeAccountId(entity.getAccountId())
                .receivingAccountId(entity.getReceivingAccountId())
                .sum(entity.getSum())
                .build();
    }

    public HistoryEntryView buildViewFromEntry(TransactionHistoryEntry entry) {
        return HistoryEntryView.builder()
                .date(entry.getDate())
                .type(OperationType.valueOf(entry.getType().name()))
                .incomeAccountId(entry.getAccountId())
                .receivingAccountId(null)
                .sum(entry.getSum())
                .build();
    }

    public HistoryEntryView buildViewFromEntry(TransactionTransferHistoryEntry entry) {
        return HistoryEntryView.builder()
                .date(entry.getDate())
                .type(OperationType.TRANSFER)
                .incomeAccountId(entry.getOutcomeAccountId())
                .receivingAccountId(entry.getReceivingAccountId())
                .sum(entry.getSum())
                .build();
    }


}
