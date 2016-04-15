package hu.horvathzoltan.dto;

import hu.horvathzoltan.dto.UserDTO;
import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.registry.infomodel.User;
import java.time.LocalDate;
import java.util.Set;

public class UserDTOTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private static UserDTO userDTO;

    @BeforeClass
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Before
    public void setUp(){
        userDTO = new UserDTO.UserBuilder().build();
    }


    /**********************************************
     * USERNAME TESTS
     **********************************************/
    @Test
    public void shouldViolateUsernameNotNullConstraint() {
        userDTO.setUsername(null);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "username");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.iterator().next().getInvalidValue());

    }
    @Test
    public void shouldViolateUsernameSizelConstraint() {
        userDTO.setUsername("Sa");
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "username");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Sa", violations.iterator().next().getInvalidValue());

    }
    @Test
    public void shouldNotViolateUsernameSizelConstraint() {
        userDTO.setUsername("Sas");
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "username");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals("Sas", userDTO.getUsername());

    }
    /**********************************************
     * PASSWORD TESTS
     **********************************************/
    @Test
    public void shouldViolatePasswordNotNullConstraint() {
        userDTO.setPassword(null);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "password");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.iterator().next().getInvalidValue());

    }
    @Test
    public void shouldViolatePasswordConstraints() {
        userDTO.setPassword("asdf");
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "password");
        Assert.assertEquals(3, violations.size());
        Assert.assertEquals("asdf", violations.iterator().next().getInvalidValue());

    }
    @Test
    public void shouldNotViolatePasswordConstraints() {
        userDTO.setPassword("Abcd1+");
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "password");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals("Abcd1+", userDTO.getPassword());

    }
    /**********************************************
     * DATE OF BIRTH TESTS
     **********************************************/
    @Test
    public void shouldViolateDateOfBirthPastConstraint() {
        userDTO.setDateOfBirth(LocalDate.of(2100,5,6));
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "dateOfBirth");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(LocalDate.of(2100,5,6), violations.iterator().next().getInvalidValue());

    }
    @Test
    public void shouldNotViolateDateOfBirthPastConstraint() {
        userDTO.setDateOfBirth(LocalDate.of(1987,5,6));
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "dateOfBirth");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(LocalDate.of(1987,5,6), userDTO.getDateOfBirth());

    }
    /**********************************************
     * REGISTRATION DATE TESTS
     **********************************************/
    @Test
    public void shouldViolateRegistrationNotNullConstraint() {
        userDTO.setRegistrationDate(null);
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "registrationDate");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.iterator().next().getInvalidValue());

    }
    @Test
    public void shouldNotViolateRegistrationNotNullConstraint() {
        userDTO.setRegistrationDate(LocalDate.of(1987,5,6));
        Set<ConstraintViolation<UserDTO>> violations = validator.validateProperty(userDTO, "registrationDate");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(LocalDate.of(1987,5,6), userDTO.getRegistrationDate());

    }
    /**********************************************
     * CHRONOLOGICAL DATES TESTS
     **********************************************/
    @Test
    public void shouldViolateChronologicalDateConstraint() {
        userDTO = new UserDTO.UserBuilder()
                .setUsername("Elek")
                .setPassword("Abcd1+")
                .setDateOfBirth(LocalDate.of(1999,1,1))
                .setRegistrationDate(LocalDate.of(1988,1,1))
                .build()

        ;
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertEquals("Date of Birth must be before date of registration.", violations.iterator().next().getMessage());
        Assert.assertEquals(1, violations.size());


    }
    @Test
    public void shouldNotViolateChronologicalDateConstraint() {
        userDTO = new UserDTO.UserBuilder()
                .setUsername("Elek")
                .setPassword("Abcd1+")
                .setDateOfBirth(LocalDate.of(1988,1,1))
                .setRegistrationDate(LocalDate.of(1999,1,1))
                .build()

        ;
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(LocalDate.of(1988,1,1), userDTO.getDateOfBirth());
        Assert.assertEquals(LocalDate.of(1999,1,1), userDTO.getRegistrationDate());


    }
}
