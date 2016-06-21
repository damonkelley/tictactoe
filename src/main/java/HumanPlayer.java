import java.awt.*;

public class HumanPlayer extends Player {

    public HumanPlayer(PlayerMarker marker) {
        super(marker);
    }

    public void move(State state, Point space) {
        state.move(space, marker);
    }

    public void move(State state) {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return marker == player.marker;
    }

}
