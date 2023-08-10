package dkoryakin.aston.demo.app.result;

import dkoryakin.aston.demo.api.body.response.AccountView;
import dkoryakin.aston.demo.domain.event.OperationDoneEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;


import java.util.Optional;


@Getter
@AllArgsConstructor
public class OperationResult {

    private Status status;
    private String description;
    private OperationDoneEvent event;
    private AccountView result;




    public Optional<OperationDoneEvent> getEvent() {
        return Optional.ofNullable(event);
    }


    public ResponseEntity<?> toResponse() {
        return switch (status) {
            case SUCCESS -> ResponseEntity.ok(result);
            case FAIL -> ResponseEntity.badRequest().body(description);
        };
    }
}
