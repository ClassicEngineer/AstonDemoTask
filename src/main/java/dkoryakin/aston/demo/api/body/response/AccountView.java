package dkoryakin.aston.demo.api.body.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class AccountView {

    private Long id;

    private String name;

    private BigDecimal balance;


}
