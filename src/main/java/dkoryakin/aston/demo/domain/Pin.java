package dkoryakin.aston.demo.domain;

import dkoryakin.aston.demo.domain.exception.PinValidationException;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.regex.Pattern;

@Log
@Getter
public class Pin {

    private final static Pattern PATTERN = Pattern.compile("[0-9]{4}");

    private final String value;

    public static Pin valueOf(String pin) {
        validate(pin);
        return new Pin(pin);
    }

    public static void validate(String pin) {
        if (pin == null || !PATTERN.matcher(pin).matches()) {
            throw new PinValidationException("String: " + pin + " is not valid pin");
        }
    }

    private Pin(String value) {
        this.value = value;
    }
}
