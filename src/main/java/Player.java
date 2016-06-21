import java.awt.*;

public abstract class Player {
    protected PlayerMarker marker;

    public Player(PlayerMarker marker) {
        this.marker = marker;
    }

    abstract public void move(State state);
    abstract public void move(State state, Point space);

    public PlayerMarker getMarker() {
        return marker;
    }
}
