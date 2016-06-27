import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegerRangeInputValidatorTest {

    @Test
    public void nonIntegersAreNotValid() {
        IntegerRangeInputValidator validator = new IntegerRangeInputValidator(1, 9);

        assertFalse(validator.isValid("a"));
        assertFalse(validator.isValid("check a multi-word string"));
    }

    @Test
    public void symbolsAreNotValid() {
        IntegerRangeInputValidator validator = new IntegerRangeInputValidator(1, 9);

        assertFalse(validator.isValid("$"));
        assertFalse(validator.isValid("#&*(:"));
    }

    @Test
    public void itAllowsIntegersWithinARange() {
        IntegerRangeInputValidator validator = new IntegerRangeInputValidator(1, 9);

        assertTrue(validator.isValid("1"));
        assertTrue(validator.isValid("5"));
        assertTrue(validator.isValid("6"));
        assertTrue(validator.isValid("9"));
    }

    @Test
    public void itDisallowsIntegersOutsideARange() {
        IntegerRangeInputValidator validator = new IntegerRangeInputValidator(1, 9);

        assertFalse(validator.isValid("-10"));
        assertFalse(validator.isValid("0"));
        assertFalse(validator.isValid("10"));
    }
}
