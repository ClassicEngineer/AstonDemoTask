package dkoryakin.aston.demo.api;

import dkoryakin.aston.demo.app.HistoryApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class TransactionHistoryRestController {


    private final HistoryApplicationService historyApplicationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getHistoryTransactionsByAccountId(@PathVariable("id") Long id) {
        var result = historyApplicationService.getHistoryTransactionsByAccountId(id);
        return ResponseEntity.ok(result);
    }

}
