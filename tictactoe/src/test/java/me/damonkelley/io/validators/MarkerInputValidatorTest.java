package me.damonkelley.io.validators;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MarkerInputValidatorTest {
    @Test
    public void itValidatesX() {
        assertTrue(new MarkerInputValidator().isValid("X"));
    }

    @Test
    public void itValidatesO() {
        assertTrue(new MarkerInputValidator().isValid("O"));
    }

    @Test
    public void allOtherInputIsInvalid() {
        assertFalse(new MarkerInputValidator().isValid("o"));
        assertFalse(new MarkerInputValidator().isValid("foo"));
        assertFalse(new MarkerInputValidator().isValid("1"));
        assertFalse(new MarkerInputValidator().isValid("#$@#$"));
        assertFalse(new MarkerInputValidator().isValid(null));
    }
}
