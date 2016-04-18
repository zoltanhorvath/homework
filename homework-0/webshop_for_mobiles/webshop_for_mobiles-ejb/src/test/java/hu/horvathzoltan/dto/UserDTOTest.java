package hu.horvathzoltan.dto;

import hu.horvathzoltan.dto.UserDTO;
import org.junit.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

public class UserDTOTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private static LocalDate dateOfBirthForTests = LocalDate.of(1984, 2, 19);
    private static LocalDate dateOfRegistrationFroTests = LocalDate.of(2016, 11, 1);


    @BeforeClass
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    /**********************************************
     * USERNAME TESTS
     **********************************************/

    @Test
    public void shouldViolateUsernameNotNullConstraint() {
        UserDTO userDTO = new UserDTO(null, "Password1", "Admin", "Janos", dateOfBirthForTests, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "username");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldViolateUsernameSizelConstraint() {
        UserDTO userDTO = new UserDTO("Sa", "Password1", "Admin", "Janos", dateOfBirthForTests, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "username");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Sa", violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldNotViolateUsernameSizelConstraint() {
        UserDTO userDTO = new UserDTO("San", "Password1", "Admin", "Janos", dateOfBirthForTests, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "username");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals("San", userDTO.getUsername());
    }

    /**********************************************
     * PASSWORD TESTS
     **********************************************/

    @Test
    public void shouldViolatePasswordNotNullConstraint() {
        UserDTO userDTO = new UserDTO("admin", null, "Admin", "Janos", dateOfBirthForTests, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "password");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldViolatePasswordConstraints() {
        UserDTO userDTO = new UserDTO("admin", "asdf", "Admin", "Janos", dateOfBirthForTests, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "password");
        Assert.assertEquals(3, violations.size());
        Assert.assertEquals("asdf", violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldNotViolatePasswordConstraints() {
        UserDTO userDTO = new UserDTO("admin", "Abcd1+", "Admin", "Janos", dateOfBirthForTests, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "password");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals("Abcd1+", userDTO.getPassword());
    }

    /**********************************************
     * DATE OF BIRTH TESTS
     **********************************************/

    @Test
    public void shouldViolateDateOfBirthPastConstraint() {
        LocalDate invalidDateOfBirth = LocalDate.of(2100, 2, 19);
        UserDTO userDTO = new UserDTO("admin", "Password1", "Admin", "Janos", invalidDateOfBirth, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "dateOfBirth");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(invalidDateOfBirth, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldNotViolateDateOfBirthPastConstraint() {
        UserDTO userDTO = new UserDTO("admin", "Password1", "Admin", "Janos", dateOfBirthForTests, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "dateOfBirth");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(dateOfBirthForTests, userDTO.getDateOfBirth());
    }

    /**********************************************
     * REGISTRATION DATE TESTS
     **********************************************/

    @Test
    public void shouldViolateRegistrationNotNullConstraint() {
        UserDTO userDTO = new UserDTO("admin", "Password1", "Admin", "Janos", dateOfBirthForTests, null, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "registrationDate");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.iterator().next().getInvalidValue());

    }

    @Test
    public void shouldNotViolateRegistrationNotNullConstraint() {
        UserDTO userDTO = new UserDTO("admin", "Password1", "Admin", "Janos", dateOfBirthForTests, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "registrationDate");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(dateOfRegistrationFroTests, userDTO.getRegistrationDate());
    }

    /**********************************************
     * CHRONOLOGICAL DATES TESTS
     **********************************************/

    @Test
    public void shouldViolateChronologicalDateConstraint() {
        UserDTO userDTO = new UserDTO("admin", "Password1", "Admin", "Janos", dateOfBirthForTests, dateOfRegistrationFroTests, true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertEquals("Date of Birth must be before date of registration.", violations.iterator().next().getMessage());
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldNotViolateChronologicalDateConstraint() {
        UserDTO userDTO = new UserDTO("admin", "Password1", "Admin", "Janos", LocalDate.of(1984, 2, 19), LocalDate.of(2016, 11, 1), true);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertEquals(0, violations.size());
    }
}
