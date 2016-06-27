public class IntegerRangeInputValidator {
    private int low;
    private int high;

    public IntegerRangeInputValidator(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public boolean isValid(String input) {
        if (!canParse(input)) return false;
        if (isInRange(Integer.parseInt(input))) return true;
        return false;
    }

    private boolean isInRange(Integer input) {
        return input >= low && input <= high;
    }

    private boolean canParse(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
