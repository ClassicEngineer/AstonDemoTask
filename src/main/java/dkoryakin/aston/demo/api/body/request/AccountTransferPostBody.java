package dkoryakin.aston.demo.api.body.request;

import dkoryakin.aston.demo.infrastructure.validator.PinCodeConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransferPostBody {

    @PinCodeConstraint
    private String pin;

    @NotNull
    private Double amount;

    @NotNull
    private Long accountId;
}
