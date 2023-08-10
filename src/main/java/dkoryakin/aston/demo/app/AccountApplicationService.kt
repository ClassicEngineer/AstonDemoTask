package dkoryakin.aston.demo.app

import dkoryakin.aston.demo.api.body.request.AccountCreatePostBody
import dkoryakin.aston.demo.api.body.request.AccountDepositPostBody
import dkoryakin.aston.demo.api.body.request.AccountTransferPostBody
import dkoryakin.aston.demo.api.body.request.AccountWithdrawPostBody
import dkoryakin.aston.demo.api.body.response.AccountsGetResponseBody
import dkoryakin.aston.demo.app.result.AccountCreationResult
import dkoryakin.aston.demo.app.result.OperationResult
import dkoryakin.aston.demo.domain.Account
import dkoryakin.aston.demo.domain.Pin
import dkoryakin.aston.demo.domain.event.OperationDoneEvent
import dkoryakin.aston.demo.domain.service.AccountService
import dkoryakin.aston.demo.infrastructure.factory.AccountFactory
import lombok.RequiredArgsConstructor
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AccountApplicationService(private val accountService: AccountService,
        private val factory: AccountFactory,
        private val eventPublisher: ApplicationEventPublisher) {

    fun createAccount(body: AccountCreatePostBody): AccountCreationResult {
        val account = accountService.createAccount(body.name, Pin.valueOf(body.pin))
        return AccountCreationResult.success(factory.buildViewFromAccount(account))
    }

    val allAccounts: AccountsGetResponseBody
        get() {
            val views = accountService.retrieveAllAccounts().stream()
                .map { account: Account? -> factory.buildViewFromAccount(account) }
                .toList()
            return AccountsGetResponseBody(views)
        }

    fun makeDeposit(accountId: Long?, body: AccountDepositPostBody): OperationResult {
        val result = accountService.makeDeposit(accountId, body.amount)
        result.event.ifPresent { event: OperationDoneEvent? -> eventPublisher.publishEvent(event) }
        return result
    }

    fun makeWithdraw(accountId: Long, body: AccountWithdrawPostBody): OperationResult {
        val result = accountService.makeWithdraw(accountId, Pin.valueOf(body.pin), body.getAmount())
        result.event.ifPresent { event: OperationDoneEvent? -> eventPublisher.publishEvent(event) }
        return result
    }

    fun makeTransfer(accountId: Long?, body: AccountTransferPostBody): OperationResult {
        val result = accountService!!.makeTransfer(accountId, body.accountId, Pin.valueOf(body.pin), body.amount)
        result.event.ifPresent { event: OperationDoneEvent? -> eventPublisher.publishEvent(event) }
        return result
    }
}
