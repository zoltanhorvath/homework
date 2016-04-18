package hu.horvathzoltan.validator;

import hu.horvathzoltan.annotation.Past;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PastValidator implements ConstraintValidator<Past, LocalDate> {

    @Override
    public void initialize(Past past) {
        // Empty
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return true;
        } else {
            return localDate.isBefore(LocalDate.now());
        }
    }
}
