package me.damonkelley.io.validators;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayerTypeInputValidatorTest {
    @Test
    public void HIsValidInput() {
        assertTrue(new PlayerTypeInputValidator().isValid("H"));
    }

    @Test
    public void CIsValidInput() {
        assertTrue(new PlayerTypeInputValidator().isValid("C"));
    }

    @Test
    public void allOtherInputIsInvalid() {
        assertFalse(new PlayerTypeInputValidator().isValid("X"));
        assertFalse(new PlayerTypeInputValidator().isValid("c"));
        assertFalse(new PlayerTypeInputValidator().isValid("h"));
        assertFalse(new PlayerTypeInputValidator().isValid("1"));
        assertFalse(new PlayerTypeInputValidator().isValid("computer"));
        assertFalse(new PlayerTypeInputValidator().isValid("human"));
        assertFalse(new PlayerTypeInputValidator().isValid(null));
    }
}
