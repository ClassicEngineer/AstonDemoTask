package dkoryakin.aston.demo.infrastructure.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PinCodeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PinCodeConstraint {
    String message() default "Invalid pin code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}