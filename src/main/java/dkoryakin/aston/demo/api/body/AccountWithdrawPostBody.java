package dkoryakin.aston.demo.api.body;

import dkoryakin.aston.demo.infrastructure.validator.PinCodeConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountWithdrawPostBody {

    @PinCodeConstraint
    private String pin;

    public Double amount;

}
