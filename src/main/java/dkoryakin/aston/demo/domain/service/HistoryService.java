package dkoryakin.aston.demo.domain.service;

import dkoryakin.aston.demo.domain.TransactionHistoryEntry;
import dkoryakin.aston.demo.domain.event.OperationDoneEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Log
@Transactional
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public void handle(OperationDoneEvent event) {
        TransactionHistoryEntry entry = TransactionHistoryEntry.Factory.from(event);
        historyRepository.save(entry);
        log.info("Event was saved to history: " + event);
    }

    public Collection<TransactionHistoryEntry> getHistoryTransactionsById(Long accountId) {
        return historyRepository.findAllByAccountId(accountId);
    }
}
