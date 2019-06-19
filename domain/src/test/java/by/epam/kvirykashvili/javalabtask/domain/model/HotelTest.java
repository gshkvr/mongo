package by.epam.kvirykashvili.javalabtask.domain.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class HotelTest {
    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void starsBetweenOneAndFive() {
        Hotel hotel = Hotel.builder()
                .id(5)
                .name("Hilton")
                .stars(6)
                .website("hilton.com")
                .latitude("45.23765")
                .longitude("48.58233")
                .features(new Features[]{Features.BALCONY, Features.WIFI, Features.BREAKFAST})
                .build();

        Set<ConstraintViolation<Hotel>> constraintViolations = validator.validate(hotel);

        assertEquals(1, constraintViolations.size());
//        assertEquals("must be less than or equal to 5", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void nameIsNull() {
        Hotel hotel = Hotel.builder()
                .id(5)
                .stars(3)
                .website("hilton.com")
                .latitude("45.23765")
                .longitude("48.58233")
                .features(new Features[]{Features.BALCONY, Features.WIFI, Features.BREAKFAST})
                .build();

        Set<ConstraintViolation<Hotel>> constraintViolations = validator.validate(hotel);

        assertEquals(1, constraintViolations.size());
//        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void latitudeIsNull() {
        Hotel hotel = Hotel.builder()
                .id(5)
                .name("Hilton")
                .stars(3)
                .website("hilton.com")
                .longitude("48.58233")
                .features(new Features[]{Features.BALCONY, Features.WIFI, Features.BREAKFAST})
                .build();

        Set<ConstraintViolation<Hotel>> constraintViolations = validator.validate(hotel);

        assertEquals(1, constraintViolations.size());
//        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void longitudeIsNull() {
        Hotel hotel = Hotel.builder()
                .id(5)
                .name("Hilton")
                .stars(3)
                .website("hilton.com")
                .latitude("45.23765")
                .features(new Features[]{Features.BALCONY, Features.WIFI, Features.BREAKFAST})
                .build();

        Set<ConstraintViolation<Hotel>> constraintViolations = validator.validate(hotel);

        assertEquals(1, constraintViolations.size());
//        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void featuresIsNull() {
        Hotel hotel = Hotel.builder()
                .id(5)
                .name("Hilton")
                .stars(3)
                .website("hilton.com")
                .latitude("45.23765")
                .longitude("48.58233")
                .build();

        Set<ConstraintViolation<Hotel>> constraintViolations = validator.validate(hotel);

        assertEquals(1, constraintViolations.size());
//        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

}