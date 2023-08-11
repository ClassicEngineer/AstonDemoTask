package dkoryakin.aston.demo.infrastructure.listener;

import dkoryakin.aston.demo.domain.event.OperationDoneEvent;
import dkoryakin.aston.demo.domain.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationDoneEventListener {

    private final HistoryService historyService;

    // IT may be TransactionEventListener
    @EventListener
    public void handleOperationDoneEvent(OperationDoneEvent event) {
        switch (event.getType()) {
            case DEPOSIT, WITHDRAW -> historyService.saveToHistory(event);
            case TRANSFER -> historyService.saveTransferToHistory(event);
        }
    }
}
