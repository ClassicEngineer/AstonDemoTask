package dkoryakin.aston.demo.api.body.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDepositPostBody {

    @NotNull
    private BigDecimal amount;

}
