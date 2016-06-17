import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private Map<Point, Player> spaces;

    public Board() {
        this.spaces = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.spaces.put(new Point(i, j), null);
            }
        }
    }

    public Player get(Point point) {
        return spaces.get(point);
    }

    public void put(Point point, Player marker) {
        spaces.put(point, marker);
    }

    public boolean isFull() {
        return spaces.entrySet()
                .stream()
                .noneMatch(m -> m.getValue() == null);
    }
}