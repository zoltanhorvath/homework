package hu.horvathzoltan.validator;

import hu.horvathzoltan.annotation.ChronologicalDate;
import hu.horvathzoltan.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChronologicalDateValidator implements ConstraintValidator<ChronologicalDate, UserDTO> {

    @Override
    public void initialize(ChronologicalDate constraint) {
        // Empty
    }
    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        if (null == userDTO.getDateOfBirth()) {
            return true;
        }

        return userDTO.getDateOfBirth().isBefore(userDTO.getRegistrationDate());
    }
}
