package hu.horvathzoltan.producer;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;


public class ValidatorFactoryProducer {


    @Produces
    public ValidatorFactory createValidator() {
        return Validation.buildDefaultValidatorFactory();

    }
}
