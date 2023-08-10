package dkoryakin.aston.demo.api

import dkoryakin.aston.demo.api.body.response.HistoryEntryView
import dkoryakin.aston.demo.app.HistoryApplicationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
class TransactionHistoryRestController( private val historyApplicationService: HistoryApplicationService) {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{accountId}")
    @Operation(summary = "Get all history for account with given id")
    @ApiResponse(responseCode = "200", description = "History for account with id",
            content = [Content(mediaType = "application/json",
                    array = ArraySchema(schema = Schema(implementation = HistoryEntryView::class)))])
    fun retHistoryTransactionsByAccountId(@PathVariable("accountId") accountId: Long?): ResponseEntity<*> {
        val result = historyApplicationService.getHistoryTransactionsByAccountId(accountId)
        return ResponseEntity.ok(result)
    }
}
