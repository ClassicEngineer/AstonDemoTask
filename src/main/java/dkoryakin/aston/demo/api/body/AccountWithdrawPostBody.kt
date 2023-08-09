package dkoryakin.aston.demo.api.body

import dkoryakin.aston.demo.infrastructure.validator.PinCodeConstraint
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length


data class AccountWithdrawPostBody(@NotNull val amount:Double,
                                   @PinCodeConstraint val pin: String) {
}