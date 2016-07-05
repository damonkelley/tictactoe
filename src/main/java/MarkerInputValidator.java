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
