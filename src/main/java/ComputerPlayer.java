import java.awt.*;

public class ComputerPlayer {
    private PlayerMarker marker;

    public ComputerPlayer(PlayerMarker marker) {
        this.marker = marker;
    }

    public void move(State state) {
        state.move(new Point(0, 0), PlayerMarker.X);
    }

    public PlayerMarker getMarker() {
        return marker;
    }
}
