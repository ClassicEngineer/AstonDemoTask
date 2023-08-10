package dkoryakin.aston.demo.domain

import kotlin.math.abs

data class Account(private val id: Long,
        private val name: String,
        private val pin: Pin, var balance: Double) {

    fun deposit(amount: Double) {
        balance += amount
    }

    fun withdraw(amount: Double) {
        balance -= abs(amount)
    }

    fun transfer(amount: Double, destination: Account) {
        balance -= amount
        destination.balance += amount
    }
}
