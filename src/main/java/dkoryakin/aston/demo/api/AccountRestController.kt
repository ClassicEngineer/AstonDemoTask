package dkoryakin.aston.demo.api

import dkoryakin.aston.demo.api.body.request.AccountCreatePostBody
import dkoryakin.aston.demo.api.body.request.AccountDepositPostBody
import dkoryakin.aston.demo.api.body.request.AccountTransferPostBody
import dkoryakin.aston.demo.api.body.request.AccountWithdrawPostBody
import dkoryakin.aston.demo.api.body.response.AccountView
import dkoryakin.aston.demo.app.AccountApplicationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.util.function.Consumer

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
class AccountRestController(private val accountApplicationService: AccountApplicationService) {

    @PostMapping("/create")
    @Operation(summary = "Create account with name and pin")
    @ApiResponse(responseCode = "200", description = "Created account", content = [Content(mediaType = "application/json", schema = Schema(implementation = AccountView::class))])
    fun createAccount(@RequestBody body: @Valid AccountCreatePostBody): ResponseEntity<*> {
        val result = accountApplicationService.createAccount(body)
        return result.toResponse()
    }

    @ApiResponse(responseCode = "200", description = "Created account", content = [Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = AccountView::class)))])
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all accounts")
    @GetMapping("/all")
    fun getAllAccounts(): ResponseEntity<*> {
        val result = accountApplicationService.getAllAccounts()
        return ResponseEntity.ok(result)
    }

    @PostMapping("/{account_id}/deposit")
    @Operation(summary = "Making deposit for account with given amount")
    @ApiResponse(responseCode = "200", description = "Account after making deposit", content = [Content(mediaType = "application/json", schema = Schema(implementation = AccountView::class))])
    fun deposit(@PathVariable(name = "account_id") accountId: Long?, @RequestBody body: @Valid AccountDepositPostBody?): ResponseEntity<*> {
        val result = accountApplicationService.makeDeposit(accountId, body)
        return result.toResponse()
    }

    @PostMapping("/{account_id}/withdraw")
    @Operation(summary = "Making withdraw for account with given amount and pin")
    @ApiResponse(responseCode = "200", description = "Account after making withdraw", content = [Content(mediaType = "application/json", schema = Schema(implementation = AccountView::class))])
    fun withdraw(@PathVariable(name = "account_id") accountId: Long?, @RequestBody body: @Valid AccountWithdrawPostBody?): ResponseEntity<*> {
        val result = accountApplicationService.makeWithdraw(accountId, body)
        return result.toResponse()
    }

    @PostMapping("/{account_id}/transfer")
    @Operation(summary = "Making transfer for account with given amount pin and transferAccountId")
    @ApiResponse(responseCode = "200", description = "Account after making transfer", content = [Content(mediaType = "application/json", schema = Schema(implementation = AccountView::class))])
    fun transfer(@PathVariable(name = "account_id") accountId: Long?, @RequestBody body: @Valid AccountTransferPostBody?): ResponseEntity<*> {
        val result = accountApplicationService.makeTransfer(accountId, body)
        return result.toResponse()
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): Map<String, String> {
        val errors: MutableMap<String, String> = HashMap()
        exception.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        return errors
    }
}
