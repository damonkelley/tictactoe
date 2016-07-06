package me.damonkelley.io.validators;

public class PlayerTypeInputValidator implements Validator {

    public final String HUMAN_TYPE = "H";
    public final String COMPUTER_TYPE = "C";

    public boolean isValid(String type) {
        return HUMAN_TYPE.equals(type) || COMPUTER_TYPE.equals(type);
    }
}
