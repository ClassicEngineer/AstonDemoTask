package dkoryakin.aston.demo.api.body;

import dkoryakin.aston.demo.infrastructure.validator.PinCodeConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatePostBody {

    @NotBlank
    @Length(min = 3, max = 50)
    private String name;

    @PinCodeConstraint
    private String pin;
}
