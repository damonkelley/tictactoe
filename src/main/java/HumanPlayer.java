import java.awt.*;

public class HumanPlayer extends Player {

    public HumanPlayer(PlayerMarker marker) {
        super(marker);
    }

    public void move(State state, Point space) {
        state.move(space, marker);
    }

    public void move(State state) {}
}
