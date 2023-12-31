package dkoryakin.aston.demo.api;

import dkoryakin.aston.demo.api.body.request.AccountDepositPostBody;
import dkoryakin.aston.demo.api.body.request.AccountTransferPostBody;
import dkoryakin.aston.demo.api.body.request.AccountCreatePostBody;
import dkoryakin.aston.demo.api.body.request.AccountWithdrawPostBody;
import dkoryakin.aston.demo.api.body.response.AccountView;
import dkoryakin.aston.demo.app.AccountApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountRestController {

    private final AccountApplicationService accountApplicationService;

    @PostMapping("/create")
    @Operation(summary = "Create account with name and pin")
    @ApiResponse(responseCode = "200", description = "Created account",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountView.class)) })
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountCreatePostBody body) {
        var result = accountApplicationService.createAccount(body);
        return result.toResponse();
    }

    @GetMapping("/all")
    @Operation(summary = "Get all accounts")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Created account",
            content = { @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountView.class))) })
    public ResponseEntity<?> getAllAccounts() {
        var result = accountApplicationService.getAllAccounts();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{account_id}/deposit")
    @Operation(summary = "Making deposit for account with given amount")
    @ApiResponse(responseCode = "200", description = "Account after making deposit",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountView.class)) })
    public ResponseEntity<?> deposit(@PathVariable(name = "account_id") Long accountId, @RequestBody @Valid AccountDepositPostBody body) {
        var result = accountApplicationService.makeDeposit(accountId, body);
        return result.toResponse();
    }
    @PostMapping("/{account_id}/withdraw")
    @Operation(summary = "Making withdraw for account with given amount and pin")
    @ApiResponse(responseCode = "200", description = "Account after making withdraw",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountView.class)) })
    public ResponseEntity<?> withdraw(@PathVariable(name = "account_id") Long accountId, @RequestBody @Valid AccountWithdrawPostBody body) {
        var result = accountApplicationService.makeWithdraw(accountId, body);
        return result.toResponse();
    }

    @PostMapping("/{account_id}/transfer")
    @Operation(summary = "Making transfer for account with given amount pin and transferAccountId")
    @ApiResponse(responseCode = "200", description = "Account after making transfer",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountView.class)) })
    public ResponseEntity<?> transfer(@PathVariable(name = "account_id") Long accountId, @RequestBody @Valid AccountTransferPostBody body) {
        var result = accountApplicationService.makeTransfer(accountId, body);
        return result.toResponse();
    }

}
