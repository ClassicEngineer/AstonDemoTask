package dkoryakin.aston.demo.app

import dkoryakin.aston.demo.api.body.response.HistoryGetResponseBody
import dkoryakin.aston.demo.domain.TransactionHistoryEntry
import dkoryakin.aston.demo.domain.service.HistoryService
import dkoryakin.aston.demo.infrastructure.factory.TransactionHistoryFactory
import org.springframework.stereotype.Service

@Service
class HistoryApplicationService(private val historyService: HistoryService,
        private val factory: TransactionHistoryFactory) {

    fun getHistoryTransactionsByAccountId(accountId: Long?): HistoryGetResponseBody {
        val results = historyService.getHistoryTransactionsById(accountId)
            .stream()
            .map { entry: TransactionHistoryEntry -> factory.buildViewFromEntry(entry) }
            .toList()
        return HistoryGetResponseBody(results)
    }
}
