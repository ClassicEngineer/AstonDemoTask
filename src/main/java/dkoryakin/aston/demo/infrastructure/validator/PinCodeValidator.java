package dkoryakin.aston.demo.infrastructure.validator;

import dkoryakin.aston.demo.domain.Pin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PinCodeValidator implements ConstraintValidator<PinCodeConstraint, String> {

    @Override
    public boolean isValid(String pin, ConstraintValidatorContext context) {
        return pin != null && Pin.PATTERN.matcher(pin).matches();
    }
}
