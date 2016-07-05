package me.damonkelley.io.validators;

import me.damonkelley.tictactoe.Marker;

public class MarkerInputValidator implements Validator {
    public boolean isValid(String x) {
        try {
            Marker.valueOf(x);
            return true;
        } catch (IllegalArgumentException | NullPointerException e){
            return false;
        }
    }
}
