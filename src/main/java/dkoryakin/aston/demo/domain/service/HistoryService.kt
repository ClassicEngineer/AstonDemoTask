package dkoryakin.aston.demo.domain.service

import dkoryakin.aston.demo.domain.TransactionHistoryEntry
import dkoryakin.aston.demo.domain.event.OperationDoneEvent
import dkoryakin.aston.demo.domain.repository.HistoryRepository
import dkoryakin.aston.demo.infrastructure.factory.TransactionHistoryFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.logging.Logger

@Service
@Transactional
open class HistoryService(private val historyRepository: HistoryRepository,
                          private val factory: TransactionHistoryFactory) {
    fun handle(event: OperationDoneEvent) {
        val entry = factory.buildEntryFromEvent(event)
        historyRepository.save(entry)
        log.info("Event was saved to history: $event")
    }

    fun getHistoryTransactionsById(accountId: Long?): Collection<TransactionHistoryEntry> {
        return historyRepository.findAllByAccountId(accountId)
    }

    companion object {
        private val log = Logger.getLogger(HistoryService::class.java.getName())
    }
}
