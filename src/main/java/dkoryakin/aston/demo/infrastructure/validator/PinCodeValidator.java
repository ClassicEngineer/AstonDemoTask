package dkoryakin.aston.demo.infrastructure.validator;

import dkoryakin.aston.demo.domain.Pin;
import dkoryakin.aston.demo.domain.exception.PinValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PinCodeValidator implements ConstraintValidator<PinCodeConstraint, String> {

    @Override
    public boolean isValid(String pin, ConstraintValidatorContext context) {
        try {
            Pin.validate(pin);
        } catch (PinValidationException e) {
            return false;
        }
        return true;
    }
}
