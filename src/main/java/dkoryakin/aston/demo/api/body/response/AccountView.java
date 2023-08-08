package dkoryakin.aston.demo.api.body.response;

import dkoryakin.aston.demo.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AccountView {

    private Long id;

    private String name;

    private Double balance;


    public static AccountView from(Account account) {
        return AccountView.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .name(account.getName())
                .build();
    }

}
