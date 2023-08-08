package dkoryakin.aston.demo.app;

import dkoryakin.aston.demo.api.body.response.HistoryEntryView;
import dkoryakin.aston.demo.api.body.response.HistoryGetResponseBody;
import dkoryakin.aston.demo.domain.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryApplicationService {

    private final HistoryService historyService;

    public HistoryGetResponseBody getHistoryTransactionsByAccountId(Long accountId) {
        var results = historyService.getHistoryTransactionsById(accountId)
                .stream()
                .map(HistoryEntryView::from)
                .toList();
        return new HistoryGetResponseBody(results);
    }
}
