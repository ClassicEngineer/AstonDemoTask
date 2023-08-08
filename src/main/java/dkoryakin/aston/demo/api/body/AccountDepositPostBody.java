package dkoryakin.aston.demo.api.body;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDepositPostBody {

    private Double amount;

}
