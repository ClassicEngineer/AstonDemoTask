package dkoryakin.aston.demo.domain.service

import dkoryakin.aston.demo.app.result.OperationResult
import dkoryakin.aston.demo.domain.Account
import dkoryakin.aston.demo.domain.Pin
import dkoryakin.aston.demo.domain.repository.AccountRepository
import dkoryakin.aston.demo.infrastructure.factory.OperationResultFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.math.abs

@Service
@Transactional
open class AccountService(
    private val accountRepository: AccountRepository,
    private val resultFactory: OperationResultFactory
) {
    fun createAccount(name: String?, pin: Pin?): Account {
        return accountRepository.create(name, pin!!)
    }

    @Transactional(readOnly = true)
    open fun retrieveAllAccounts(): Collection<Account> {
        return accountRepository.findAll()
    }

    @Transactional(readOnly = true)
    open fun findAccountByIdAndPin(accountId: Long?, pin: Pin?): Optional<Account> {
        return accountRepository.findAccountByIdAndPin(accountId, pin!!)
    }

    fun makeDeposit(accountId: Long, amount: Double): OperationResult {
        val accountOptional = findAccountById(accountId)
        return if (accountOptional.isPresent) {
            val account = accountOptional.get()
            if (validateDeposit(amount)) {
                account.deposit(amount)
                save(account)
                resultFactory.successDeposit(account, amount)
            } else {
                resultFactory.invalidOperation("Deposit must me positive")
            }
        } else {
            resultFactory.nonAuthorized()
        }
    }

    fun makeWithdraw(accountId: Long, pin: Pin, amount: Double): OperationResult {
        val accountOptional = findAccountByIdAndPin(accountId, pin)
        return if (accountOptional.isPresent) {
            val account = accountOptional.get()
            if (validateWithdraw(amount, account.balance)) {
                account.withdraw(amount)
                save(account)
                resultFactory.successWithdraw(account, amount)
            } else {
                resultFactory.invalidOperation("Withdraw must me less or equal balance")
            }
        } else {
            resultFactory.nonAuthorized()
        }
    }

    fun makeTransfer(accountId: Long?, transferToAccountId: Long, pin: Pin?, amount: Double): OperationResult {
        val accountOptional = findAccountByIdAndPin(accountId, pin)
        return if (accountOptional.isPresent) {
            val account = accountOptional.get()
            val destinationAccount = findAccountById(transferToAccountId)
            if (validateTransfer(amount, account.balance)) {
                if (destinationAccount.isPresent) {
                    val destination = destinationAccount.get()
                    account.transfer(amount, destination)
                    save(account)
                    save(destination)
                } else {
                    return resultFactory.invalidOperation("Account with id: $transferToAccountId is not exist.")
                }
                resultFactory.successTransfer(account, amount, transferToAccountId)
            } else {
                resultFactory.invalidOperation("Transfer sum must be less or equal balance")
            }
        } else {
            resultFactory.nonAuthorized()
        }
    }

    private fun findAccountById(accountId: Long): Optional<Account> {
        return accountRepository.findAccountById(accountId)
    }

    private fun validateTransfer(amount: Double, balance: Double): Boolean {
        return amount > 0 && amount <= balance
    }

    private fun validateDeposit(amount: Double): Boolean {
        return amount > 0
    }

    private fun validateWithdraw(amount: Double, balance: Double): Boolean {
        return abs(amount) <= balance
    }

    private fun save(account: Account) {
        accountRepository.save(account)
    }
}
