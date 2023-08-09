package dkoryakin.aston.demo.domain;

import dkoryakin.aston.demo.domain.exception.PinValidationException;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.regex.Pattern;


@Getter
@Log
public class Pin {

    public final static Pattern PATTERN = Pattern.compile("[0-9]{4}");

    private final String value;

    public static Pin valueOf(String str) {
        try {
            String pin = str.substring(0, 4);
            if (!PATTERN.matcher(pin).matches()) {
                throw new IllegalArgumentException(str);
            }
            return new Pin(pin);
        } catch (Exception e) {
            log.warning("String: " + str + " is not valid pin");
            throw new PinValidationException(e);
        }
    }

    private Pin(String value) {
        this.value = value;
    }
}
