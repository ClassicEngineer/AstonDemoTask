package dkoryakin.aston.demo.api.body

import dkoryakin.aston.demo.infrastructure.validator.PinCodeConstraint
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length


data class AccountTransferPostBody(@PinCodeConstraint val pin: String,
                                   @NotNull val amount:Double,
                                    @NotNull val accountId: Long
) { }