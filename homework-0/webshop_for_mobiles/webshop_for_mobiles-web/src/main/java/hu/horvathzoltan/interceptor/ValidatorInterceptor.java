package hu.horvathzoltan.interceptor;

import hu.horvathzoltan.annotation.BeanValidation;
import hu.horvathzoltan.annotation.Validate;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

@Interceptor
@BeanValidation
public class ValidatorInterceptor {


    @Inject
    private ValidatorFactory validatorFactory;

    private Validator validator = validatorFactory.getValidator();

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) throws Exception {
        validateParameters(invocationContext.getParameters());
        return invocationContext.proceed();
    }

    private void validateParameters(Object[] parameters) {
        for (Object parameter : parameters) {
            if (parameter.getClass().isAnnotationPresent(Validate.class)) {
                validateBean(parameter);
            }
        }
    }

    private void validateBean(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream().map(e -> "Validation error: " + e.getMessage() + " - property: " + e.getPropertyPath().toString() + " . ").reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }
}