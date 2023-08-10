package dkoryakin.aston.demo.api.body.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountsGetResponseBody {

    private List<AccountView> accounts;
}
