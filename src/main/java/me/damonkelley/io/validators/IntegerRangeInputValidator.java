package me.damonkelley.io.validators;

public class IntegerRangeInputValidator implements Validator {
    private int low;
    private int high;

    public IntegerRangeInputValidator(int low, int high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public boolean isValid(String input) {
        try {
            return isInRange(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInRange(Integer input) {
        return input >= low && input <= high;
    }
}
