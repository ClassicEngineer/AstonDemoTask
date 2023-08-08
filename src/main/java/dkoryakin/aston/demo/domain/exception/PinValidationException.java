package dkoryakin.aston.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PinValidationException extends RuntimeException {
    private String invalidPin;
    public PinValidationException(String invalidPin, Exception e) {
        super(e);
        this.invalidPin = invalidPin;
    }
}
