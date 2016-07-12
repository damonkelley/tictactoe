package me.damonkelley.io.validators;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SpaceIDInputValidatorTest {
    @Test(expected = InputValidationError.class)
    public void itRaisesAnExceptionIfTheInputIsInvalid() {
        new SpaceIDInputValidator(1, 9).isValid("10");
    }

    @Test
    public void itIsValidIfItIsWithinTheRange() {
        Validator validator = new SpaceIDInputValidator(1, 9);

        assertTrue(validator.isValid("1"));
        assertTrue(validator.isValid("5"));
        assertTrue(validator.isValid("9"));
    }
}
