package hu.horvathzoltan.dto;

import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

public class MobileDTOTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private static MobileDTO mobileDTO;

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
    public void setUp() {
        mobileDTO = new MobileDTO();
    }


    /**********************************************
     * ID TESTS
     **********************************************/
    @Test
    public void shouldViolateIdNotNullConstraint() {
        mobileDTO.setId(null);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "id");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.iterator().next().getInvalidValue());

    }

    @Test
    public void shouldViolateIdLengthConstraint() {
        mobileDTO.setId("fasdfasdvyx");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "id");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("fasdfasdvyx", violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldNotViolateIdLengthConstraint() {
        String generatedUUID = UUID.randomUUID().toString();
        mobileDTO.setId(generatedUUID);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "id");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(generatedUUID, mobileDTO.getId());

    }

    /**********************************************
     * TYPE TESTS
     **********************************************/

    @Test
    public void shouldViolateTypeNotNullConstraint() {
        mobileDTO.setType(null);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "type");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldViolateTypeLengthConstraint() {
        mobileDTO.setType("aa");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "type");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("aa", violations.iterator().next().getInvalidValue());

    }

    @Test
    public void shouldNotViolateTypeLengthConstraint() {
        mobileDTO.setType("aaa");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "type");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals("aaa", mobileDTO.getType());
    }

    /**********************************************
     * MANUFACTURER TESTS
     **********************************************/
    @Test
    public void shouldViolateManufacturerNotNullConstraint() {
        mobileDTO.setManufacturer(null);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "manufacturer");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldViolateManufacturerLengthConstraint() {
        mobileDTO.setManufacturer("aa");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "manufacturer");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("aa", violations.iterator().next().getInvalidValue());

    }

    @Test
    public void shouldNotViolateManufacturerLengthConstraint() {
        mobileDTO.setManufacturer("aaa");
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "manufacturer");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals("aaa", mobileDTO.getManufacturer());
    }

    /**********************************************
     * PRICE TESTS
     **********************************************/
    @Test
    public void shouldViolatePriceMinConstraint() {
        mobileDTO.setPrice(0);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "price");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(0, mobileDTO.getPrice());
    }

    @Test
    public void shouldNotViolatePriceMinConstraint() {
        mobileDTO.setPrice(1);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "price");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(1, mobileDTO.getPrice());
    }

    /**********************************************
     * PIECE TESTS
     **********************************************/
    @Test
    public void shouldViolatePieceMinConstraint() {
        mobileDTO.setPiece(-1);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "piece");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(-1, mobileDTO.getPiece());
    }

    @Test
    public void shouldNotViolatePieceMinConstraint() {
        mobileDTO.setPiece(0);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "piece");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(0, mobileDTO.getPiece());
    }

}
