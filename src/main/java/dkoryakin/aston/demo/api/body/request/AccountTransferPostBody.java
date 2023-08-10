package dkoryakin.aston.demo.api.body.request;

import dkoryakin.aston.demo.infrastructure.validator.PinCodeConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransferPostBody {

    @PinCodeConstraint
    private String pin;

    private Double amount;

    private Long accountId;
}
