package dkoryakin.aston.demo.app.result;

import dkoryakin.aston.demo.api.body.response.AccountView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@ToString
@Getter
public class AccountCreationResult {

    private Status status;
    private String description;

    private AccountView value;


    public static AccountCreationResult success(AccountView account) {
        return new AccountCreationResult(Status.SUCCESS, "Account successfully created", account);
    }


    public ResponseEntity<?> toResponse() {
        return switch (status) {
            case SUCCESS -> ResponseEntity.ok().body(value);
            case FAIL -> ResponseEntity.badRequest().body(description);
        };
    }
}
