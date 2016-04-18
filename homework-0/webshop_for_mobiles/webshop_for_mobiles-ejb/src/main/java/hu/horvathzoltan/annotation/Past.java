package hu.horvathzoltan.annotation;

import hu.horvathzoltan.validator.PastValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastValidator.class)
public @interface Past {

    String message() default "Date must be in the Past.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}