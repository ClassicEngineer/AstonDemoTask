package dkoryakin.aston.demo.api.body

import dkoryakin.aston.demo.infrastructure.validator.PinCodeConstraint
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length


data class AccountCreatePostBody(@NotBlank @Length(min = 3, max = 50)val name:String,
                                 @PinCodeConstraint val pin: String) {
}

