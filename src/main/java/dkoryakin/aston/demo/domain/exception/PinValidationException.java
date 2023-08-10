package dkoryakin.aston.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PinValidationException extends RuntimeException {
    public PinValidationException(String message) {
        super(message);
    }
}
