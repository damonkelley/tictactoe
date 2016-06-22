import java.awt.*;
import java.util.*;

public class QueueBackedPlayer extends Player {

    private Queue<Point> moveQueue = new ArrayDeque<>();

    public QueueBackedPlayer(Marker marker) {
        super(marker);
    }

    public void move(State state) {
        state.move(moveQueue.remove(), getMarker());
    }

    public void queueMove(Point space) {
        moveQueue.add(space);
    }

}
