import java.awt.*;
import java.util.*;

public class HumanPlayer extends Player {

    private Queue<Point> moveQueue = new ArrayDeque<>();

    public HumanPlayer(PlayerMarker marker) {
        super(marker);
    }

    public void move(State state) {
        state.move(moveQueue.remove(), getMarker());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return marker == player.marker;
    }

    public void queueMove(Point space) {
        moveQueue.add(space);
    }

}
