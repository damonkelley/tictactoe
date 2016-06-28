import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegerInputValidatorTest {

    @Test
    public void integersAreValid() {
        assertTrue(new IntegerInputValidator().isValid("1"));
    }

    @Test
    public void nonIntegersAreNotValid() {
        assertFalse(new IntegerInputValidator().isValid("a"));
        assertFalse(new IntegerInputValidator().isValid("check a multi-word string"));
    }

    @Test
    public void symbolsAreNotValid() {
        assertFalse(new IntegerInputValidator().isValid("$"));
        assertFalse(new IntegerInputValidator().isValid("#&*(:"));
    }
}
