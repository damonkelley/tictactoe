import java.awt.*;

public class Player {
    private final PlayerMarker marker;

    public Player(PlayerMarker marker) {
        this.marker = marker;
    }

    public void move(State state, Point point) {
        state.move(point, marker);
    }

    public PlayerMarker getMarker() {
        return marker;
    }
}
