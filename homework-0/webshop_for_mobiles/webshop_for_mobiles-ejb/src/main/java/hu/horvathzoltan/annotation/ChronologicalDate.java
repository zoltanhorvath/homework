package hu.horvathzoltan.annotation;

import hu.horvathzoltan.validator.ChronologicalDateValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChronologicalDateValidator.class)
public @interface ChronologicalDate {

    String message() default "Dates must be chronological.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}