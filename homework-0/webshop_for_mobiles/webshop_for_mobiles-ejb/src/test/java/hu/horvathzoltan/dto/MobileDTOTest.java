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
		String testId = "fasdfasdvyx";
        mobileDTO.setId(testId);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "id");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(testId, violations.iterator().next().getInvalidValue());
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
		String testType = "aa";
        mobileDTO.setType(testType);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "type");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(testType, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldNotViolateTypeLengthConstraint() {
		String testType = "aaa";
        mobileDTO.setType(testType);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "type");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(testType, mobileDTO.getType());
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
		String testManufacturer = "aa";
		mobileDTO.setManufacturer(testManufacturer);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "manufacturer");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(testManufacturer, violations.iterator().next().getInvalidValue());
    }

    @Test
    public void shouldNotViolateManufacturerLengthConstraint() {
		String testManufacturer = "aaa";
        mobileDTO.setManufacturer(testManufacturer);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "manufacturer");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(testManufacturer, mobileDTO.getManufacturer());
    }

    /**********************************************
     * PRICE TESTS
     **********************************************/

    @Test
    public void shouldViolatePriceMinConstraint() {
		int testPrice = 0;
        mobileDTO.setPrice(testPrice);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "price");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(testPrice, mobileDTO.getPrice());
    }

    @Test
    public void shouldNotViolatePriceMinConstraint() {
		int testPrice = 1;
        mobileDTO.setPrice(testPrice);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "price");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(testPrice, mobileDTO.getPrice());
    }

    /**********************************************
     * PIECE TESTS
     **********************************************/

    @Test
    public void shouldViolatePieceMinConstraint() {
		int testPiece = -1;
        mobileDTO.setPiece(testPiece);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "piece");
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(testPiece, mobileDTO.getPiece());
    }

    @Test
    public void shouldNotViolatePieceMinConstraint() {
		int testPiece = 0;
        mobileDTO.setPiece(0);
        Set<ConstraintViolation<MobileDTO>> violations = validator.validateProperty(mobileDTO, "piece");
        Assert.assertEquals(0, violations.size());
        Assert.assertEquals(testPiece, mobileDTO.getPiece());
    }
}
