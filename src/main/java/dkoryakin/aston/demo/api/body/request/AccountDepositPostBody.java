package dkoryakin.aston.demo.api.body.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDepositPostBody {

    private Double amount;

}
