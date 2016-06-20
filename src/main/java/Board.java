import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Point, PlayerMarker> spaces;

    public Board() {
        this.spaces = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.spaces.put(new Point(i, j), null);
            }
        }
    }

    public PlayerMarker get(Point point) {
        return spaces.get(point);
    }

    public void put(Point point, PlayerMarker marker) {
        spaces.put(point, marker);
    }

    public boolean isFull() {
        return spaces.entrySet()
                .stream()
                .noneMatch(m -> m.getValue() == null);
    }
}