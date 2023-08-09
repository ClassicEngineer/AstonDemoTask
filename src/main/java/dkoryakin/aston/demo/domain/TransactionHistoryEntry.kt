package dkoryakin.aston.demo.domain

import dkoryakin.aston.demo.domain.event.OperationDoneEvent
import lombok.Builder
import lombok.Getter
import java.util.*


@Builder
@Getter
class TransactionHistoryEntry(val type: OperationType,
                              val date: Date,
                              val incomeAccountId: Long,
                              val receivingAccountId: Long? = null,
                              val sum: Double) {


    companion object Factory {
        fun from(event: OperationDoneEvent): TransactionHistoryEntry {
            return TransactionHistoryEntry(
                    date = event.date,
                    incomeAccountId = event.accountInId,
                    receivingAccountId = event.accountOutId,
                    sum = event.sum,
                    type = event.type
            );
        }
    }
}
