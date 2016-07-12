package me.damonkelley.io.validators;

public class SpaceIDInputValidator implements Validator {

    private Validator rangeValidator;

    public SpaceIDInputValidator(int low, int high) {
        rangeValidator = new IntegerRangeInputValidator(low, high);
    }

    @Override
    public boolean isValid(String input) {
        if (!rangeValidator.isValid(input)) {
            throw new InputValidationError(input + " is not a valid space");
        }
        return true;
    }
}
