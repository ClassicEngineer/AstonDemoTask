package dkoryakin.aston.demo.app;

import dkoryakin.aston.demo.api.body.response.HistoryGetResponseBody;
import dkoryakin.aston.demo.domain.service.HistoryService;
import dkoryakin.aston.demo.infrastructure.factory.TransactionHistoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HistoryApplicationService {

    private final HistoryService historyService;
    private final TransactionHistoryFactory factory;

    public HistoryGetResponseBody getHistoryTransactionsByAccountId(Long accountId) {
        var singleOperationResults = historyService.getHistoryTransactionsById(accountId)
                .stream()
                .map(factory::buildViewFromEntry)
                .toList();
        var transferResults = historyService.getTransferHistoryTransactionsById(accountId)
                .stream()
                .map(factory::buildViewFromEntry)
                .toList();
        var results = Stream.concat(transferResults.stream(), singleOperationResults.stream()).toList();
        return new HistoryGetResponseBody(results);
    }
}
