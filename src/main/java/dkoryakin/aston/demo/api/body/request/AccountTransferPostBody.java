package dkoryakin.aston.demo.api.body.request;

import dkoryakin.aston.demo.infrastructure.validator.PinCodeConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransferPostBody {

    @PinCodeConstraint
    private String pin;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long accountId;
}
